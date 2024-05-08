import jwt

algorithm = "HS256"
data = {
    "caramba21": {"value": "the secret is you"},
    "izabela": {"value": "I am the secret"},
    "arka": {"value": "There is no secret"}
}
secret = "bmil"

encoded = jwt.encode(data, secret, algorithm=algorithm)
print(encoded)

open("encoded_message.txt", "w").write(encoded)

decoded_message = jwt.decode(encoded, secret, algorithms=[algorithm])
print(decoded_message)
