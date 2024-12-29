package com.agusteam.travelo

import com.agusteam.travelo.data.dao.CategoryDao
import com.agusteam.travelo.data.dao.TripsDao
import com.agusteam.travelo.data.dao.UserProfileDao
import com.agusteam.travelo.data.impl.CategoryRepositoryImp
import com.agusteam.travelo.data.impl.TripRepositoryImp
import com.agusteam.travelo.data.impl.UserProfileRepositoryImp
import com.agusteam.travelo.data.impl.UserSignUpRepositoryImp
import com.agusteam.travelo.data.validations.FieldValidator
import com.agusteam.travelo.domain.usecase.*
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.exceptions.UnknownRestException
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.datetime.*


fun calculateMonthsSince(createdAt: Instant): Int {
    // Get the current time and the system's time zone
    val currentDateTime = Clock.System.now()
    val timeZone = TimeZone.currentSystemDefault()

    // Convert Instant to LocalDate
    val createdDate = createdAt.toLocalDateTime(timeZone).date
    val currentDate = currentDateTime.toLocalDateTime(timeZone).date

    // Calculate the difference in months
    return createdDate.until(currentDate, DateTimeUnit.MONTH)
}


fun getSignUpUseCase(): SignUpUserUseCase {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://fjuwfnwhfdtdlflnlmnp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqdXdmbndoZmR0ZGxmbG5sbW5wIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQ3Mzc1MzUsImV4cCI6MjA1MDMxMzUzNX0.hpYG66JoG0_wWNsPv0b6nFcdiaGi-yodY-De0frik28"
    ) {
        install(Auth)
        install(Postgrest)
    }
    return SignUpUserUseCase(
        UserSignUpRepositoryImp(
            supabase = getAdminSupaBase(), userProfileDao = UserProfileDao(supabase)
        ), FieldValidator()
    )
}

fun getAdminSupaBase(): SupabaseClient {
    return createSupabaseClient(
        supabaseUrl = "https://fjuwfnwhfdtdlflnlmnp.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZqdXdmbndoZmR0ZGxmbG5sbW5wIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTczNDczNzUzNSwiZXhwIjoyMDUwMzEzNTM1fQ.A4P-gN48OnDRA7-NUb0_HxHm_7B2UMViUswhG3_C4do"
    ) {
        install(Auth)
        install(Postgrest)
    }
}

fun getGetProfileDetailsUseCase(): GetProfileDetailsUseCase {
    return GetProfileDetailsUseCase(UserProfileRepositoryImp(UserProfileDao(getAdminSupaBase())))
}

fun geBusinessProfileDetailsUseCase(): CreteBusinessProfileUseCase {
    return CreteBusinessProfileUseCase(
        FieldValidator(),
        UserProfileRepositoryImp(
            UserProfileDao(getAdminSupaBase())
        )
    )
}

fun getGetBusinessProfileUseCase(): GetBusinessProfileUseCase {
    return GetBusinessProfileUseCase(
        UserProfileRepositoryImp(
            UserProfileDao(getAdminSupaBase())
        )
    )
}

fun getCategoriesUseCase(): GetCategoriesUseCase {
    return GetCategoriesUseCase(CategoryRepositoryImp(CategoryDao(supabase = getAdminSupaBase())))
}


fun getCreateTripUseCase(): CreateTripUseCase {
    return CreateTripUseCase(TripRepositoryImp(TripsDao(getAdminSupaBase())))
}

fun getPaginatedTripUseCase(): GetPaginatedTripUseCase {
    return GetPaginatedTripUseCase(TripRepositoryImp(TripsDao(getAdminSupaBase())))
}

fun getSetFavoriteTripUsecase(): SetTripFavoriteUseCase {
    return SetTripFavoriteUseCase(TripRepositoryImp(TripsDao(getAdminSupaBase())))
}

fun getRemoveFavoriteTripUsecase(): RemoveTripFavoriteUseCase {
    return RemoveTripFavoriteUseCase(TripRepositoryImp(TripsDao(getAdminSupaBase())))
}

fun getTripIncludeServicesUsecase(): GetTripIncludedServicesUseCase {
    return GetTripIncludedServicesUseCase(TripRepositoryImp(TripsDao(getAdminSupaBase())))
}

fun getErrorMessage(errorCode: String): String {
    return when (errorCode) {
        "anonymous_provider_disabled" -> "Las autenticaciones anónimas están deshabilitadas."
        "bad_code_verifier" -> "El verificador de código proporcionado no coincide con el esperado. Indica un error en la implementación de la biblioteca cliente."
        "bad_json" -> "El cuerpo HTTP de la solicitud no es un JSON válido."
        "bad_jwt" -> "El JWT enviado en el encabezado de Autorización no es válido."
        "bad_oauth_callback" -> "La devolución de llamada OAuth desde el proveedor a Auth no tiene todos los atributos requeridos (estado). Indica un problema con el proveedor OAuth o la implementación de la biblioteca cliente."
        "bad_oauth_state" -> "El estado OAuth (datos devueltos por el proveedor OAuth a Supabase Auth) no está en el formato correcto. Indica un problema con la integración del proveedor OAuth."
        "captcha_failed" -> "El desafío de captcha no pudo ser verificado con el proveedor de captcha. Verifica tu integración de captcha."
        "conflict" -> "Conflicto general de base de datos, como solicitudes concurrentes en recursos que no deben modificarse simultáneamente."
        "email_address_invalid" -> "Los dominios de ejemplo y prueba no son compatibles actualmente. Por favor, utiliza una dirección de correo diferente."
        "email_address_not_authorized" -> "No se permite enviar correos electrónicos a esta dirección, ya que tu proyecto está utilizando el servicio SMTP predeterminado. Solo se pueden enviar correos electrónicos a miembros de tu organización de Supabase."
        "email_conflict_identity_not_deletable" -> "Desvincular esta identidad causará que la cuenta del usuario cambie a una dirección de correo electrónico que ya está siendo utilizada por otra cuenta de usuario."
        "email_exists" -> "La dirección de correo electrónico ya existe en el sistema."
        "email_not_confirmed" -> "No se permite iniciar sesión ya que la dirección de correo electrónico no está confirmada."
        "email_provider_disabled" -> "Los registros están deshabilitados para correo electrónico y contraseña."
        "flow_state_expired" -> "El estado del flujo PKCE ha caducado. Pide al usuario que inicie sesión nuevamente."
        "flow_state_not_found" -> "El estado del flujo PKCE ya no existe. Los estados del flujo caducan después de un tiempo y se limpian progresivamente."
        "hook_payload_invalid_content_type" -> "El payload de Auth no tiene un encabezado Content-Type válido."
        "hook_payload_over_size_limit" -> "El payload de Auth excede el límite de tamaño máximo."
        "hook_timeout" -> "No se pudo alcanzar el hook dentro del tiempo máximo asignado."
        "hook_timeout_after_retry" -> "No se pudo alcanzar el hook después del número máximo de reintentos."
        "identity_already_exists" -> "La identidad a la que se refiere la API ya está vinculada a un usuario."
        "identity_not_found" -> "La identidad a la que se refiere la llamada API no existe."
        "insufficient_aal" -> "Para llamar a esta API, el usuario debe tener un nivel más alto de Autenticación de Aseguramiento."
        "invite_not_found" -> "La invitación ha caducado o ya ha sido utilizada."
        "invalid_credentials" -> "Las credenciales de inicio de sesión o el tipo de concesión no son reconocidos."
        "manual_linking_disabled" -> "La vinculación manual de usuarios no está habilitada en el servidor Auth."
        "mfa_challenge_expired" -> "Responder al desafío MFA debe hacerse dentro de un período de tiempo determinado. Solicita un nuevo desafío."
        "mfa_factor_name_conflict" -> "Los factores MFA para un solo usuario no deben tener el mismo nombre amigable."
        "mfa_factor_not_found" -> "El factor MFA ya no existe."
        "mfa_ip_address_mismatch" -> "El proceso de inscripción para los factores MFA debe comenzar y finalizar con la misma dirección IP."
        "mfa_phone_enroll_not_enabled" -> "La inscripción de factores MFA con teléfono está deshabilitada."
        "mfa_phone_verify_not_enabled" -> "La verificación de factores MFA con teléfono está deshabilitada."
        "mfa_totp_enroll_not_enabled" -> "La inscripción de factores MFA TOTP está deshabilitada."
        "mfa_totp_verify_not_enabled" -> "La verificación de factores MFA TOTP está deshabilitada."
        "mfa_verification_failed" -> "El desafío MFA no pudo ser verificado -- código TOTP incorrecto."
        "mfa_verification_rejected" -> "Se rechaza la verificación MFA. Esto solo se devuelve si el intento de verificación MFA es rechazado."
        "mfa_verified_factor_exists" -> "El factor de teléfono verificado ya existe para un usuario. Desinscribe el factor de teléfono verificado existente para continuar."
        "mfa_web_authn_enroll_not_enabled" -> "La inscripción de factores MFA Web Authn está deshabilitada."
        "mfa_web_authn_verify_not_enabled" -> "La verificación de factores MFA WebAuthn está deshabilitada."
        "no_authorization" -> "Esta solicitud HTTP requiere un encabezado de Autorización, que no se ha proporcionado."
        "not_admin" -> "El usuario que accede a la API no es un administrador."
        "oauth_provider_not_supported" -> "Se está utilizando un proveedor OAuth que está deshabilitado en el servidor Auth."
        "otp_disabled" -> "Iniciar sesión con OTP (enlace mágico, OTP por correo) está deshabilitado. Verifica la configuración del servidor."
        "otp_expired" -> "El código OTP para este inicio de sesión ha caducado. Pide al usuario que inicie sesión nuevamente."
        "over_email_send_rate_limit" -> "Se han enviado demasiados correos electrónicos a esta dirección. Pide al usuario que espere un momento antes de intentarlo nuevamente."
        "over_request_rate_limit" -> "Se han enviado demasiadas solicitudes desde esta dirección IP. Pide al usuario que intente nuevamente en unos minutos."
        "over_sms_send_rate_limit" -> "Se han enviado demasiados mensajes SMS a este número. Pide al usuario que espere un momento antes de intentarlo nuevamente."
        "phone_exists" -> "El número de teléfono ya existe en el sistema."
        "phone_not_confirmed" -> "No se permite iniciar sesión ya que el número de teléfono no está confirmado."
        "phone_provider_disabled" -> "Los registros están deshabilitados para teléfono y contraseña."
        "provider_disabled" -> "El proveedor OAuth está deshabilitado para su uso. Verifica la configuración de tu servidor."
        "provider_email_needs_verification" -> "No todos los proveedores OAuth verifican la dirección de correo electrónico de sus usuarios. Supabase Auth requiere que los correos electrónicos sean verificados."
        "reauthentication_needed" -> "El usuario necesita reautenticarse para cambiar su contraseña. Pide al usuario que se reautentique."
        "reauthentication_not_valid" -> "La verificación de la reautenticación falló, el código es incorrecto."
        "refresh_token_not_found" -> "No se encontró la sesión que contiene el token de actualización."
        "refresh_token_already_used" -> "El token de actualización ha sido revocado y está fuera del intervalo de reutilización."
        "request_timeout" -> "El procesamiento de la solicitud tardó demasiado. Intenta nuevamente."
        "same_password" -> "El usuario debe usar una contraseña diferente de la actual para actualizarla."
        "saml_assertion_no_email" -> "La afirmación SAML recibida después del inicio de sesión no contiene una dirección de correo electrónico, lo cual es obligatorio."
        "saml_assertion_no_user_id" -> "La afirmación SAML no contiene un identificador de usuario válido."
        else -> "Error desconocido. Verifica los detalles del código de error."
    }
}

// Define a sealed class for custom error handling
sealed class RestError {
    object BadRequest : RestError()
    object Unauthorized : RestError()
    object Forbidden : RestError()
    object NotFound : RestError()
    object Conflict : RestError()
    object TooManyRequests : RestError()
    object InternalServerError : RestError()
    object ServiceUnavailable : RestError()
    data class CustomError(val message: String) : RestError()
    data class UnknownError(val exception: Exception) : RestError()
}

// Map error codes or exceptions to RestError
fun mapToRestError(exception: UnknownRestException): RestError {
    return when (exception.statusCode) {
        400 -> RestError.BadRequest
        401 -> RestError.Unauthorized
        403 -> RestError.Forbidden
        404 -> RestError.NotFound
        409 -> RestError.Conflict
        429 -> RestError.TooManyRequests
        500 -> RestError.InternalServerError
        503 -> RestError.ServiceUnavailable
        else -> RestError.UnknownError(exception)
    }
}

// Example usage
fun handleApiException(exception: UnknownRestException): String {
    val error = mapToRestError(exception)
    return when (error) {
        is RestError.BadRequest -> "Solicitud incorrecta: Verifica los datos enviados en la solicitud."
        is RestError.Unauthorized -> "No autorizado: Revisa tu clave API o token de autenticación."
        is RestError.Forbidden -> "Prohibido: No tienes permisos para acceder a este recurso."
        is RestError.NotFound -> "No encontrado: El recurso solicitado no existe."
        is RestError.Conflict -> "Conflicto: Hay un conflicto con el estado actual del recurso."
        is RestError.TooManyRequests -> "Demasiadas solicitudes: Has excedido el límite de peticiones."
        is RestError.InternalServerError -> "Error interno del servidor: Algo salió mal en el servidor."
        is RestError.ServiceUnavailable -> "Servicio no disponible: Supabase podría estar en mantenimiento o sobrecargado."
        is RestError.UnknownError -> "Error desconocido: ${error.exception.message}"
        is RestError.CustomError -> "Error personalizado: ${error.message}"
    }
}
