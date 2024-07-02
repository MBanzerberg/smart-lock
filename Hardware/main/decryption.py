import base64
import datetime

import rsa
import jwt

id = 0
algorithm = "HS256"
secret = "bmil"

with open("../asymetric_keys/keys/private.pem", "rb") as file:
    private_key = rsa.PrivateKey.load_pkcs1(file.read())

with open("encrypted_message.txt", "rb") as encrypted_file:
    base64_encrypted_message = encrypted_file.read()

encrypted_message = base64.b64decode(base64_encrypted_message)

decrypted_message = rsa.decrypt(encrypted_message, private_key)

decoded_message = jwt.decode(decrypted_message, secret, algorithms=[algorithm])
value = list(decoded_message.values())[id]

if value.startswith("b'") and value.endswith("'"):
    value = value[2:-1]

date_format = "%Y-%m-%d %H:%M:%S.%f"

datetime_value = datetime.datetime.strptime(value, date_format)

now = datetime.datetime.now()

difference = now - datetime_value

print(difference)

