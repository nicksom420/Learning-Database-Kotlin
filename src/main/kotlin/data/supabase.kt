package org.example.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://svqtufrrvbtnfcsqffaa.supabase.co",
    supabaseKey = "sb_publishable_ZbtBT2b-yfJF4XEePUM4RQ_r13k_LW4"
) {
    install(Postgrest)
    install(Auth) {

    }
}