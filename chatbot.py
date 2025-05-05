def welcome():
    print("****** Welcome to Food Shop ******")
    print()


def info():
    global name
    name = input("Can I know your good name: ")
    print(f"Hey {name}! Good to see you here!!!")
    print("You are in the right place, I will help you book your dish and drinks.")
    print()


def menu():
    print('''Here is your menu with their prices
1. Pizza - RS.200
2. Mango Juice - RS.70
3. Apple Juice - RS.80''')


def order(price, product):
    print(f"\nThank you for choosing {product}\n")
    print("Please give us some more information about you!!!!")
    mob = input("Enter your mobile number: ")
    add = input("Enter your delivery address: ")

    print('''Choose mode of payment:
1. Online
2. Cash on delivery''')

    pay = input("Enter payment option (1 or 2): ")
    paymode = "Online"  # Default payment mode

    if pay == "2":
        paymode = "Cash on delivery"

    print("\n******* Here is the detail of the selected order ******\n")
    print(f"Customer Name: {name}")
    print(f"Product Name: {product}")
    print(f"Price: RS.{price}")
    print(f"Mode of Payment: {paymode}")
    print(f"Delivery Address: {add}\n")

    b = input("Please confirm by pressing 1, else 0 to cancel: ")
    print()

    if b == "1":
        print("\n******* Your order is booked successfully ******* ")
        print(f"Customer Name: {name}")
        print(f"Product Name: {product}")
        print(f"Price: RS.{price}")
        print(f"Mode of Payment: {paymode}")
        print(f"Delivery Address: {add}\n")
        print("Thank you for your interest!!! Have a nice day!!!")
    else:
        print("***** Thank you for visiting!!! Have a nice day!!! *****")


def choice():
    print("Please select a product you wish to order: ")
    a = input().lower()  # Convert input to lowercase for consistency

    if a == "pizza":
        order(200, "Pizza")
    elif a == "mango juice":
        order(70, "Mango Juice")
    elif a == "apple juice":
        order(80, "Apple Juice")
    else:
        print("You have not selected any valid product... Thank you for visiting!!! Have a nice day!!!!")


# Call the functions in sequence to run the program
welcome()
info()
menu()
choice()
