import segno

with open('encrypted_message.txt', 'rb') as file:
    my_file = file.read()

qrcode = segno.make_qr(my_file)
qrcode.save("../qr_codes/my_hello_qr.png",
            scale=5)
            #border=1,
            #light="lightblue",
            #dark="darkblue",
            #data_dark="red")


