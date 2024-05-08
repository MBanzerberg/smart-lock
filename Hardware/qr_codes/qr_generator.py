import segno

with open('D:/Users/barte/PycharmProjects/first_project/asymetric_keys/encrypted.message', 'rb') as file:
    my_file = file.read()

qrcode = segno.make_qr(my_file)
qrcode.save("my_hello_qr.png",
            scale=5)
            #border=1,
            #light="lightblue",
            #dark="darkblue",
            #data_dark="red")


