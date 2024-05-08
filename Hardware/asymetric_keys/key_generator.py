import rsa

public_key, private_key = rsa.newkeys(2048)

with open("keys/public.pem", "wb") as file:
    file.write(public_key.save_pkcs1("PEM"))

with open("keys/private.pem", "wb") as file:
    file.write(private_key.save_pkcs1("PEM"))