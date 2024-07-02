import rsa
import jwt
import base64

id = 0
algorithm = "HS256"
secret = "bmil"

with open("../asymetric_keys/keys/public.pem", "rb") as file:
    public_key = rsa.PublicKey.load_pkcs1(file.read())

with open("message.txt", "rb") as message_file:
    message = message_file.read()

encoded = jwt.encode({id: str(message)}, secret, algorithm=algorithm)
print(encoded)

encrypted_message = rsa.encrypt(encoded.encode('utf-8'), public_key)

open("encrypted_message.txt", "wb").write(base64.b64encode(encrypted_message))
