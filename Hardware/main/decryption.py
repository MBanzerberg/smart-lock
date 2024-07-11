import base64
import datetime
import json

import rsa
import jwt

with open("../asymetric_keys/keys/private.pem", "rb") as file:
    private_key = rsa.PrivateKey.load_pkcs1(file.read())

with open("encrypted_message.txt", "rb") as encrypted_file:
    encrypted_message_base64 = encrypted_file.read()

try:
    encrypted_message = base64.b64decode(encrypted_message_base64)
    decrypted_message = json.loads(rsa.decrypt(encrypted_message, private_key).decode('utf-8'))

    date_now = datetime.datetime.utcnow()
    if date_now > datetime.datetime.fromisoformat(decrypted_message['expire_date']):
        print("Token Expired")
    else:
        print(decrypted_message)
        print("Door unlocked")
        user_id = decrypted_message["id"]
        with open("users.txt", "a") as file:
            file.write(f"{user_id}: {date_now}\n")

except jwt.ExpiredSignatureError:
    print("Token Expired")
except rsa.pkcs1.DecryptionError:
    print("Token Invalid")