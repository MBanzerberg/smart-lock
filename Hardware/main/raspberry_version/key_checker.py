import base64
import datetime
import json
import rsa
import serial
from time import sleep
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(14, GPIO.OUT)

ser = serial.Serial("/dev/ttyS0", 9600, timeout=1)

with open("keys/private.pem", "rb") as file:
    private_key = rsa.PrivateKey.load_pkcs1(file.read())

try:
    while True:
        rec_data = ser.readline().decode('utf-8').strip()
        if rec_data:
            try:
                encrypted_message = base64.b64decode(rec_data)
            except base64.binascii.Error as e:
                print("Key Invalid")
                continue

            try:
                decrypted_message = json.loads(rsa.decrypt(encrypted_message, private_key).decode('utf-8'))
            except rsa.DecryptionError as e:
                print("Key Invalid")
                continue

            date_now = datetime.datetime.utcnow()
            if date_now > datetime.datetime.fromisoformat(decrypted_message['expire_date']):
                print("Token Expired")
            else:
                print(decrypted_message)
                print("Door unlocked")
                GPIO.output(14, GPIO.HIGH)
                user_id = decrypted_message["id"]
                with open("users.txt", "a") as file:
                    file.write(f"{user_id}: {date_now}\n")
                sleep(5)
                GPIO.output(14, GPIO.LOW)

except KeyboardInterrupt:
    ser.close()
