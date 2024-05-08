import rsa
import jwt

id = 0
algorithm = "HS256"
secret = "bmil"

with open("../asymetric_keys/keys/private.pem", "rb") as file:
    private_key = rsa.PrivateKey.load_pkcs1(file.read())

with open("encrypted_message.txt", "r") as encrypted_file:
    encrypted_message = encrypted_file.read()

decoded_message = jwt.decode(encrypted_message, secret, algorithms=[algorithm])
value = list(decoded_message.values())[id]
print(value)

decrypted_message = rsa.decrypt(bytes(value, 'utf-8'), private_key)
print(decrypted_message)