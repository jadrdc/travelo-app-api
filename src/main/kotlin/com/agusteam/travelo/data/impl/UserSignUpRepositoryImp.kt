package com.agusteam.travelo.data.impl

import com.agusteam.travelo.domain.interfaces.UserSignUpRepository
import com.agusteam.travelo.domain.models.UserSignupModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class UserSignUpRepositoryImp(val supabase: SupabaseClient) : UserSignUpRepository {
    override suspend fun signUpUser(model: UserSignupModel) {
        val user = supabase.auth.signUpWith(Email) {
            email = model.email
            password = model.password
        }
        if (user!=null){

        }else{

        }
    }

}