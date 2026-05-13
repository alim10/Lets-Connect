package org.alimapps.letsconnect.core.common.utils

import org.alimapps.letsconnect.core.common.BuildConfig
import org.alimapps.letsconnect.core.common.BuildConfig.IMAGE_URL

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

object Const {

    const val DEFAULT_POST_NUMBER_LENGTH = 10
    const val DEFAULT_POST_TEXT_LENGTH = 50
    const val DEFAULT_POST_RANGE_MAX_VALUE = 1000000
    const val DEFAULT_POST_PERIOD = 100

    const val SECOND_TO_MILLIS = 1000
    const val DAY_TO_SECOND = 24 * 60 * 60
    const val isProd = BuildConfig.FLAVOR_build == "prod"

    /**  Response code error   */
    const val ERROR_EXCEPTION = 1001
    const val ERROR_UNAUTHORISED = 1002
    const val ERROR_IO_EXCEPTION = 1003
    const val ERROR_HTTP_EXCEPTION = 1004

    const val LOCATION_SERVICE_ID = 1000
    const val ACTION_START_LOCATION = "start_location_service"
    const val ACTION_STOP_LOCATION = "stop_location_service"

    const val INVALID_TOKEN = 999
    const val USER_COMPANY = "3"
    const val USER_INDIVIDUAL = "2"
    const val ERROR_CODE_PREFIX = "error_code_"
    const val MESSAGE_CODE_PREFIX = "message_code_"
    const val PUSH_MESSAGE_PREFIX = "push_message_"
    const val HTTP_ERROR_CODE_PREFIX = "http_error_"
    const val STRING = "string"
    const val ANDROID_PHONE = "AndroidPhone"

    /**
     * Timer Scheduled in Second for display ADS
     */
    const val ADS_SCHEDULED_SECONDS: Long = 5 // seconds
    const val UPDATE_STATUS_TIME: Long = 10 // seconds

    /**
     * App Receiver
     */
    object Action {
        const val NETWORK_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    /**
     * all date format
     */
    const val DAY = "d"
    const val YEAR = "yyyy"
    const val MONTH = "MMM"
    const val TIME_FORMAT = "HH:mm:ss"
    const val DATE_FORMAT_YMD = "yyyy-MM-dd"
    const val DATE_FORMAT_DMY = "dd MMM yyyy"
    const val DATE_FORMAT_MY = "MMM yyyy"
    const val DATE_FORMAT_MD = "MMM dd"
    const val DATE_FORMAT_MDY = "MMM dd, yyyy"
    const val DATE_TIME_FORMAT_WEB = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    /**
     * all date format
     */
    object DateFormat {
        var webFormat = SimpleDateFormat(DATE_TIME_FORMAT_WEB, Locale(SELECTED_LANGUAGE_CODE))
        var day: SimpleDateFormat = SimpleDateFormat(DAY, Locale(SELECTED_LANGUAGE_CODE))
        var month: SimpleDateFormat = SimpleDateFormat(MONTH, Locale(SELECTED_LANGUAGE_CODE))
        var year: SimpleDateFormat = SimpleDateFormat(YEAR, Locale(SELECTED_LANGUAGE_CODE))
        var timeFormat: SimpleDateFormat =
            SimpleDateFormat(TIME_FORMAT, Locale(SELECTED_LANGUAGE_CODE))
        var yearMonthDay: SimpleDateFormat =
            SimpleDateFormat(DATE_FORMAT_YMD, Locale(SELECTED_LANGUAGE_CODE))
        var dayMonthYear: SimpleDateFormat =
            SimpleDateFormat(DATE_FORMAT_DMY, Locale(SELECTED_LANGUAGE_CODE))
        var monthYear: SimpleDateFormat =
            SimpleDateFormat(DATE_FORMAT_MY, Locale(SELECTED_LANGUAGE_CODE))
        var dayMonth: SimpleDateFormat =
            SimpleDateFormat(DATE_FORMAT_MD, Locale(SELECTED_LANGUAGE_CODE))
        var decimalTwoDigitFormat: DecimalFormat =
            DecimalFormat("0.00", DecimalFormatSymbols(Locale("en")))
    }

    /**
     * Permission requestCode
     */
    const val REQUEST_CHECK_SETTINGS = 32
    const val PERMISSION_FOR_LOCATION = 2
    const val PERMISSION_FOR_CAMERA_AND_EXTERNAL_STORAGE = 3

    /**
     * Image base Url
     */
    var IMAGE_BASE_URL = IMAGE_URL

    /**
     * Default font scale for used when app font scale change
     */
    const val SELECTED_CURRENCY = "SR"
    const val LANGUAGE_AR = "ar"
    const val LANGUAGE_EN = "en"
    const val LANGUAGE_ARABIC = "Arabic"
    const val LANGUAGE_ENGLISH = "English"
    const val DEVICE_TYPE = "Android"
    var SELECTED_LANGUAGE: String = LANGUAGE_ENGLISH
    var SELECTED_LANGUAGE_CODE: String = LANGUAGE_EN

    /* Lucky Draw resource*/
    const val SUCCESS = "Success"
    const val RESPONSE_CODE = "200"

    const val ZERO: Int = 0
    const val ONE: Int = 1
    const val EMPTY: String = ""
    const val DEFAULT_CAT_COLOR: String = "#98fb98"
    const val FLOAT: Float = 0F
    const val DOUBLE: Double = 0.0
    const val FALSE: Boolean = false
    const val TRUE: Boolean = true
    const val HUNDRED: Int = 100
    const val MAX_PHONE_LENGTH: Int = 8
    const val MIN_PHONE_LENGTH: Int = 12

    /** It's use for pagination   */
    const val PAGE_DEFAULT: Int = 1
    const val PAGE_SIZE: Int = 10
//    const val PAGE_MAX: Int = PAGE_SIZE * 3
    /** Use status tab selection for user requested post */
    const val TAB_PENDING = 1
    const val TAB_PROGRESS = 2
    const val TAB_HISTORY = 3
    const val TAB_VERIFICATION = 4
    const val TAB_APPROVED = 5
    const val TAB_REJECTED = 6
    const val TAB_PUBLISHED = 7
    const val TAB_UNPUBLISHED = 8
    const val TAB_SOLD = 12
    const val TAB_DRAFT = 11
    const val TAB_TERMINATED = 10
    const val TAB_SUSPENDED = 9


    object OrderStatus {
        const val PENDING = 1
        const val ACCEPTED = 2
        const val WAITING_FOR_PAYMENT = 3
        const val PAYMENT_COMPLETED = 4
        const val START_DELIVERY_TO_CUSTOMER = 5
        const val DELIVERY_STARTED = 6
        const val CUSTOMER_RECEIVED = 7
        const val START_JOB = 8
        const val JOB_STARTED = 9
        const val START_RETURN_FROM_CUSTOMER = 10
        const val RETURN_STARTED = 11
        const val SUPPLIER_RECEIVED = 12
        const val REJECTED = 13
        const val CANCELLED = 14
        const val COMPLETED = 15
    }

    object ContactType {
        const val PHONE = 1
        const val EMAIL = 2
    }

    object ReviewFor {
        const val POST = 1
        const val COMPANY = 2
        const val USER = 3
    }

    object OrderType {
        const val PRODUCT = 1
        const val SUPPLIER = 2
        const val SPECIAL_SUPPLIER = 3
    }
    object AddressType {
        const val HOME = 1
        const val WORK = 2
        const val REST = 3
        const val OTHER = 4
    }

    object EmployeeRequestType {
        const val NEW = "new"
        const val ACTIVE = "active"
        const val TERMINATED = "terminated"
        const val PROGRESS = "progress"
        const val HISTORY = "history"
    }

    object OtpType {
        const val LOGIN = 1
        const val FORGOT = 2
    }

    object PostType {
        const val NEGOTIABLE = 1
        const val FIXED_PRICE = 2
        const val BEST_PRICE = 3
    }

    object PostRequestType {
        const val PLACE_ORDER = 1
        const val CREATE_OFFER = 2
        const val CREATE_BEST_PRICE = 3
        const val VIEW_OFFER = 4
        const val MANAGE_POST = 5
    }
    object DeliveryType {
        const val CUSTOMER_TO_PICKUP_RETURN = 1
        const val OWNER_WILL_DELIVER_RETURN = 2
        const val THIRD_PARTY_DELIVERY = 3
        const val KARWATY_HUB = 3
    }

    object specOptionType {
        const val DEFAULT_LIST = 0
        const val SINGLE_SELECT = 1
        const val MULTIPLE_SELECT = 2
        const val DROPDOWN_LIST_SELECT = 3
        const val TEXT_FIELD = 4
        const val NUMBER_FIELD = 5
        const val NUMBERS_RANGE_FIELD = 6
    }

    object Params {
        const val ACCESS_TOKEN = "access-token"
        const val USER_ID = "user_id"
        const val BUSINESS_ID = "business_id"
        const val BUSINESS_THEME_ID = "business_theme_id"
        const val SUPPLIER_ID = "supplier_id"
        const val CATEGORY_ID = "category_id"
        const val SUB_CATEGORY_ID = "sub_category_id"
        const val MAIN_CATEGORY_ID = "main_category_id"
        const val USER_TYPE = "user_type"
        const val COUNTRY_CODE = "country_code"
        const val CONTACT_ID = "contact_id"
        const val CONTACT_TYPE = "contact_type"
        const val OTP_TYPE = "otp_type"
        const val STATUS = "status"

        const val FIRST_NAME = "first_name"
        const val MIDDLE_NAME = "middle_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val PASSWORD = "password"
        const val LOGIN_BY = "login_by"
        const val PROFILE = "profile"
        const val NATIONAL_ID = "national_id"
        const val NATIONAL_ID_IMAGE = "national_id_image"
        const val COMPANY_ID = "company_id"
        const val COMPANY_IMAGE = "company_logo"
        const val CR_IMAGE = "cr_image"
        const val VAT_IMAGE = "vat_certificate_image"
        const val REMARKS = "remarks"

        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val OTP = "otp"

        const val NAME = "name"
        const val PHONE = "phone"
        const val APP_VERSION = "app_version"
        const val DEVICE_TYPE = "device_type"
        const val DEVICE_TOKEN = "device_token"
        const val ACCEPT_TERMS_AND_CONDITION = "is_customer_accepted_terms_and_conditions"

        const val ADMIN_PHONE_NO = "admin_phone"
        const val PUBLISH_PHONE_NO = "publish_phone"
        const val WHATS_UP_PHONE_NO = "whatsapp_phone"
        const val ADMIN_COUNTRY_CODE = "admin_country_code"
        const val PUBLISH_COUNTRY_CODE = "publish_country_code"
        const val WHATS_UP_COUNTRY_CODE = "whatsapp_country_code"
        const val WEBSITE = "website"
        const val SLOGAN = "slogan"
        const val FAMOUS_FOR = "famous_for"
        const val ABOUT_US = "about_us"
        const val BUSINESS_LIST = "business_list"

        const val INSTAGRAM = "instagram"
        const val FACEBOOK = "facebook"
        const val TWITTER = "twitter"
        const val SNAPCHAT = "snapchat"
        const val CR_NUMBER = "cr_number"
        const val VAT_NUMBER = "vat_number"
        const val CR_EXPIRY_DATE = "cr_expiry_date"

        /*------------------- Posting ---------------------*/
        const val ITEM_AVAILABLE_FROM = "item_available_from"
        const val TITLE = "title"
        const val TITLE_LANGUAGE = "title_languages"
        const val DESCRIPTION = "description"
        const val DESCRIPTION_LANGUAGE = "description_languages"
        const val SPECIFICATION = "specifications"
        const val FILTER = "filters"
        const val IS_ATTACHED_FILE = "is_attach_file_selected"
        const val QUANTITY_ADDED = "quantity_added"
        const val LENGTH = "length"
        const val WIDTH = "width"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val ACCESSORIES = "accessories"
        const val IS_DRAFT = "is_draft"

        const val IS_OWNER_ACCEPTED_TERMS_AND_CONDITIONS = "is_owner_accepted_terms_and_conditions"
        const val IS_CUSTOMER_TO_PICKUP_AND_RETURN = "is_customer_to_pickup_and_return"
        const val IS_OWNER_WILL_DELIVER_AND_RETURN = "is_owner_will_deliver_and_return"
        const val IS_KARWATY_MEETING_HUB_POINT = "is_karwaty_meeting_hub_point"
        const val IS_THIRD_PARTY_PICKUP_RETURN = "is_third_party_pickup_and_return"

        const val IS_AGREE_TO_PUBLISH_LOCATION = "is_agree_to_publish_location"
        const val IS_FREE_OF_CHARGE = "is_free_of_charge"
        const val IS_OWNER_ALLOWED_TO_CHAT = "is_owner_allowed_to_chat"

        const val IS_ALLOW_REQUEST_FROM_CUSTOMER = "is_allow_request_from_customer"
        const val IS_NEGOTIABLE_PRICE = "is_negotiable_price"
        const val IS_FIXED_PRICE = "is_fixed_price"
        const val IS_BEST_PRICE = "is_best_price"
        const val IS_COMPLIMENTARY = "is_complimentary"
        const val SIMILAR_POST = "similar_posts"

        const val MAXIMUM_NUMBER_OF_DAYS_POST_VISIBLE = "maximum_number_of_days_post_visible"
        const val POST_APPEARANCE_PREFERENCE = "post_appearance_preference"
        const val SALE_PRICE = "sale_price"
        const val MAXIMUM_PERIOD = "maximum_period"
        const val MINIMUM_PERIOD = "minimum_period"
        const val SERVICES = "services"
        const val OWN_SERVICE = "own_services"
        const val PHONE_CONTACT_LIST = "phone_contacts_list"

        const val CURRENT_VALUE = "current_value"
        const val SALARY = "salary"
        const val INSURANCE_COST = "insurance_cost"
        const val DEPOSIT_AMOUNT = "deposit_amount"
        const val PER_DAY = "per_day"
        const val PER_WEEK = "per_week"
        const val PER_MONTH = "per_month"
        const val PER_YEAR = "per_year"
        const val PER_TASK = "per_task"
        const val LOCATION = "location"
        const val LOCATION_ADDRESS = "locationAddress"
        const val IMAGE = "image"
        const val VIDEO = "video"

        /*----------------------------------------*/
        const val NATIONAL_ID_EXPIRY_DATE = "national_id_expiry_date"
        const val QUALIFICATION = "qualification"
        const val INCOME = "income"
        const val SPECIAL_NEEDS = "special_needs"
        const val ADDRESS = "address"
        const val NATIONAL_ID_INFO = "national_address_info"
        const val NATIONAL_ADDRESS_NUMBER = "national_address_number"
        const val COUNTRY_ID = "country_id"
        const val STREET = "street"
        const val CITY_ID = "city_id"
        const val ZIP_CODE = "zip_code"
        const val OCCUPATION = "occupation"
        const val DOB = "dob"
        const val GENDER = "gender"
        const val MARITAL_STATUS = "marital_status"

        const val IS_SMS_NOTIFICATION_PERMISSION = "is_sms_notification_permission"
        const val IS_EMAIL_NOTIFICATION_PERMISSION = "is_email_notification_permission"
        const val IS_NO_NOTIFICATION = "is_no_notification"
        const val IS_ALERT_SOUND = "is_alert_sound"
        const val IS_ALLOW_PUSH_NOTIFICATION = "is_allow_push_notification"
        const val ALL_PERMISSION = "all_permission"

        /*  join a company, manage */
        const val PAGE = "page"
        const val OFFSET = "offset"
        const val IS_APPROVE = "is_approve"
        const val FILTER_STRING = "filter_string"
        const val TYPE = "type"
        const val SEARCH_TEXT = "search_text"
        const val ORDER_ID = "order_id"
        const val ORDER_TYPE = "order_type"
        const val END_DATE = "end_date"
        const val START_DATE = "start_date"
        const val RESIDENCE_ID = "residence_id"

        const val QUANTITY = "quantity"
        const val POST_ID = "post_id"
        const val REQUEST_TYPE = "request_type"
        const val PROMO_CODE = "promo_code"
        const val PRICE_TYPE = "price_type"
        const val PROMO = "promo_type"
        const val DELIVERY_TYPE = "delivery_type"

        /*---------------My Address -----------------*/
        const val ADDRESS_TYPE = "address_type"
        const val ADDRESS_ID = "address_id"
        const val FLOOR = "floor"
        const val APARTMENT = "apartment"
        const val BUILDING = "building"
        const val LANDMARK = "landmark"
        const val LOCATION_NAME = "location_name"
        const val IS_DEFAULT_ADDRESS = "is_default"
        const val CONTACT_NUMBER = "phone_number"
        const val USER_ADDRESS_ID = "user_address_id"

        /*--------------------------------*/
        const val OFFER_PRICE = "offer_price"
        const val DELIVERY_LOCATION = "delivery_location"
        const val TEMPORARY_CART_ID = "temporary_cart_id"
        const val TEMPORARY_INVOICE_ID = "temporary_invoice_id"
        const val IS_SHOW_ALL_BUTTON = "is_show_all_button"
        const val PAYMENT_OPTION = "payment_options"
        const val IS_SUPPLIER_WAIVE_DEPOSIT_AMOUNT = "is_supplier_waived_deposit_amount"
        const val WAIVE_DEPOSIT_AMOUNT_PERCENTAGE = "waived_deposit_amount_percentage"

        const val IS_ADMIN_APPROVED = "is_admin_approved"
        const val IS_ADMIN_REJECTED = "is_admin_rejected"
        const val IS_TERMINATED = "is_terminated"
        const val IS_SUSPENDED = "is_suspended"
        const val SUSPENDED_START_DATE = "suspension_start_date"
        const val SUSPENDED_END_DATE = "suspension_end_date"
        const val TERMINATED_DATE = "terminated_date"
        const val TERMINATED_END_DATE = "terminated_end_date"
        const val AD_ID = "ad_id"
        const val IS_SOLD = "is_sold"
        const val IS_REVERT = "Is_revert_now"
        const val IS_PUBLISHED = "is_published"
        const val IS_UNPUBLISHED = "is_unpublished"
        const val IS_WAITING_FOR_ADMIN_APPROVAL = "is_waiting_for_admin_approval"

        const val RATING = "rating"
        const val REVIEW_FOR = "review_for"
        const val REVIEW_FOR_ID = "review_for_id"
        const val POST = "post"
    }

    /**
     * all activity and fragment TAG for log
     */
    object Tags {
        const val LOGIN_SCREEN = "Login Activity"
        const val SIGNGUP_SCREEN = "Signup Activity"
        const val HOME_ACTIVITY = "HOME_ACTIVITY"
        const val DASHBOARD_ACTIVITY = "DASHBOARD_ACTIVITY"
        const val BASE_ACTIVITY = "BaseActivity"
        const val LOGIN_ACTIVITY = "LOGIN_ACTIVITY"
        const val MAP_ACTIVITY = "MAP_ACTIVITY"
        const val DISCOVERY_ACTIVITY = "DISCOVERY_ACTIVITY"
        const val SPLASH_SCREEN_ACTIVITY = "SPLASH_SCREEN_ACTIVITY"
        const val UPDATE_PROFILE_ACTIVITY = "UPDATE_PROFILE_ACTIVITY"
        const val ORDER_DETAILS_ACTIVITY = "ORDER_DETAILS_ACTIVITY"
        const val DISCOVERY_FRAGMENT = "DISCOVERY_FRAGMENT"
        const val KARWATY_FRAGMENT = "KARWATY_FRAGMENT"
        const val SERVICES_FRAGMENT = "SERVICES_FRAGMENT"
        const val HOME_FRAGMENT = "HOME_FRAGMENT"
        const val PROFILE_FRAGMENT = "PROFILE_FRAGMENT"
        const val LISTING_FRAGMENT = "HOME_FRAGMENT"
    }

    object Keys {
        const val PHONE = "phoneNumber"
        const val COUNTRY_CODE = "countryCode"
        const val USER_TYPE = "userType"
        const val IS_FROM_SPLASH = "isFromSplash"
        const val IS_FROM_PROFILE = "isFromProfile"
        const val IS_FROM_CITY = "isFromCity"
        const val IS_FROM_SUPPLIER = "isFromSupplier"
        const val DATA = "data"
        const val LOCATION = "location"
        const val ADDRESS = "Address"
        const val CITY = "city"
        const val CITY_ID = "cityId"
        const val BUSINESS_ID = "businessId"
        const val BUSINESS_THEME_ID = "businessThemeId"
        const val PRODUCT_ID = "product_id"
        const val LATLNG = "latlng"
        const val TITLE = "title"
        const val LIST = "list"
        const val SETTING = "setting"
        const val CAT_TITLE = "cat_title"
        const val MAIN_CAT_TITLE = "main_cat_title"
        const val RESIDENCE_ID = "residence_id"
        const val ORDER_TYPE = "order_type"
        const val RESIDENCE_LIST = "residence_list"
        const val START_DATE = "start_date"
        const val END_DATE = "end_date"
        const val QUANTITY = "quantity"
        const val SCREEN = "screen"
        const val STATUS = "status"
        const val ORDER_ID = "order_id"
        const val IS_WAIVED = "is_waived"
        const val WAIVED_AMOUNT = "waived_amount"
        const val DEPOSIT_AMOUNT = "deposit_amount"
        const val IS_LOCATION_PUBLISH = "isLocationPublish"
    }
}