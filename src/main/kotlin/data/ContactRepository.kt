package org.example.data

import org.example.data.Contact
import io.github.jan.supabase.postgrest.from

object contactRepository {

    suspend fun getContactByName(name: String): Contact? {

        return supabase.from("Contacts").select {
            filter {
                eq("first_name", name)
            }
        }.decodeSingleOrNull<Contact>()
    }


    suspend fun AddContact(contact: Contact) {

        val contactToAdd = Contact(
            nameFirst = contact.nameFirst,
            nameMiddle = contact.nameMiddle,
            nameLast = contact.nameLast,
            email = contact.email,
            phoneNumber = contact.phoneNumber,
            address = contact.address
        )
        supabase.from("Contacts").insert(contactToAdd)
    }
}