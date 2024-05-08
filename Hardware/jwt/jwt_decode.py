import jwt

algorithm = "HS256"
secret = "bmil"

with open("D:/Users/barte/PycharmProjects/first_project/asymetric_keys/decrypted_message.txt", "rb") as file:
    encoded = file.read()

decoded_message = jwt.decode(encoded, secret, algorithms=[algorithm])
print(decoded_message)1