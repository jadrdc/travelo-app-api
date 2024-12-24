package com.agusteam.travelo.data.validations

import com.agusteam.travelo.domain.models.BusinessProfileModel
import com.agusteam.travelo.domain.models.CreateBusinessProfileModel
import com.agusteam.travelo.domain.models.UserSignupModel

class FieldValidator {

    companion object {
        private val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$")
        private val phoneRegex = Regex("^\\+?[1-9][0-9]{7,14}\$")
        private val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$")
    }

    // General field validator with customizable error messages
    private fun validateField(
        field: String,
        regex: Regex?,
        fieldName: String,
        errorEmpty: String,
        errorInvalid: String
    ): String? {
        return when {
            field.isBlank() -> errorEmpty
            regex != null && !field.matches(regex) -> errorInvalid
            else -> null
        }
    }

    // Validate a user signup model
    fun validate(user: UserSignupModel): List<String> {
        val errors = mutableListOf<String>()

        validateField(
            user.email,
            emailRegex,
            "correo electrónico",
            "El correo electrónico no puede estar vacío.",
            "Formato de correo electrónico inválido."
        )?.let { errors.add(it) }

        validateField(
            user.phone,
            phoneRegex,
            "teléfono",
            "El número de teléfono no puede estar vacío.",
            "Formato de número de teléfono inválido."
        )?.let { errors.add(it) }

        validateField(
            user.password,
            passwordRegex,
            "contraseña",
            "La contraseña no puede estar vacía.",
            "La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas, un dígito y un carácter especial."
        )?.let { errors.add(it) }

        validateField(user.name, null, "nombre", "El nombre no puede estar vacío.", "")?.let { errors.add(it) }
        validateField(user.lastname, null, "apellido", "El apellido no puede estar vacío.", "")?.let { errors.add(it) }

        return errors
    }

    // Validate a business profile model
    fun validateBusinessProfileModel(model: CreateBusinessProfileModel): List<String> {
        val errors = mutableListOf<String>()

        validateField(model.id, null, "id", "El campo 'id' no puede estar vacío.", "")?.let { errors.add(it) }
        validateField(model.name, null, "nombre", "El campo 'nombre' no puede estar vacío.", "")?.let { errors.add(it) }
        validateField(
            model.email,
            emailRegex,
            "correo electrónico",
            "El campo 'correo electrónico' no puede estar vacío.",
            "Formato de correo electrónico inválido."
        )?.let { errors.add(it) }
        validateField(
            model.phone,
            phoneRegex,
            "teléfono",
            "El campo 'teléfono' no puede estar vacío.",
            "Formato de número de teléfono inválido."
        )?.let { errors.add(it) }
        validateField(model.address, null, "dirección", "El campo 'dirección' no puede estar vacío.", "")?.let { errors.add(it) }
        validateField(model.rnc, null, "RNC", "El campo 'RNC' no puede estar vacío.", "")?.let { errors.add(it) }
        validateField(model.description, null, "descripción", "El campo 'descripción' no puede estar vacío.", "")?.let { errors.add(it) }
      /*  validateField(
            model.creation_date,
            null,
            "fecha de creación",
            "El campo 'fecha de creación' no puede estar vacío.",
            ""
        )?.let { errors.add(it) }*/

        return errors
    }
}
