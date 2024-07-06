import base64
import datetime

import rsa
import jwt

id = 0
algorithm = "HS256"
secret = "bmil"
time_exp = datetime.timedelta(seconds=45)

with open("../asymetric_keys/keys/private.pem", "rb") as file:
    private_key = rsa.PrivateKey.load_pkcs1(file.read())

with open("encrypted_message.txt", "rb") as encrypted_file:
    base64_encrypted_message = encrypted_file.read()

encrypted_message = base64.b64decode(base64_encrypted_message)

decrypted_message = rsa.decrypt(encrypted_message, private_key)

try:
    decoded_message = jwt.decode(decrypted_message, secret, algorithms=[algorithm])
    print(decoded_message)
    print("Door unlocked")
    date_now = datetime.datetime.utcnow()
    user_id = decoded_message.get('user_id')
    with open("users.txt", "a") as file:
        file.write(f"{user_id}: {date_now}\n")

except jwt.ExpiredSignatureError:
    print("Token Expired")
except jwt.InvalidTokenError:
    print("Token Invalid")