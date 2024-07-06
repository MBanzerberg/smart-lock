import rsa
import jwt
import base64
from datetime import datetime, timedelta

id = 0
algorithm = "HS256"
secret = "bmil"
payload = {"user_id": 0, "exp": datetime.utcnow() + timedelta(minutes=1)}

with open("../asymetric_keys/keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

#with open("message.txt", "rb") as message_file:
 #   message = message_file.read()

encoded = jwt.encode(payload, secret, algorithm=algorithm)
print(encoded)

encrypted_message = rsa.encrypt(encoded.encode('utf-8'), public_key)

open("encrypted_message.txt", "wb").write(base64.b64encode(encrypted_message))
