package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.example.data.Contact
import org.example.data.contactRepository





fun displayWelcomeMenu()
{
    println("1. Look Up Contact by first name")
    println("2. Add contact")
    println("3. Exit")
}

suspend fun lookUpContact() {

    println("Please enter the first name")
    var firstName = readln()
    val contact = contactRepository.getContactByName(firstName)
    println("Name: ${contact?.nameFirst}" +
            " ${if (contact?.nameMiddle == null) contact?.nameMiddle else ""}" +
            " ${contact?.nameLast}")
    println("Email: ${contact?.email}")
    println("Phone: ${contact?.phoneNumber}")
    println("Address: ${contact?.address}")
}

suspend fun addContact() {

    println("Enter the first name: ")
    var firstName = readln()
    println("Enter the middle name if they have one: ")
    var middleName = readln()
    println("Enter the last name: ")
    var lastName = readln()
    println("Enter the email: ")
    var email = readln()
    println("Enter the Phone Number: ")
    var phoneNumber = readln()
    println("Enter the address: ")
    var address = readln()

    val newContact = Contact(
        id = null,
        nameFirst = firstName,
        nameMiddle = middleName,
        nameLast = lastName,
        email = email,
        phoneNumber = phoneNumber,
        address = address
    )

    contactRepository.AddContact(newContact)


}


fun main() {

    var done = false

    while (!done)
    {
        displayWelcomeMenu()
        val selection = readln()

        when (selection) {
            "1" -> runBlocking { lookUpContact() }
            "2" -> runBlocking { addContact() }
            "3" -> done = true
        }
    }
}