/*
 *  ---license-start
 *  eu-digital-green-certificates / dgca-verifier-app-android
 *  ---
 *  Copyright (C) 2021 T-Systems International GmbH and all other contributors
 *  ---
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  ---license-end
 *
 *  Created by mykhailo.nester on 5/5/21 11:07 PM
 */

package it.ministerodellasalute.verificaC19sdk.model

import java.security.cert.Certificate

/**
 *
 * This data class represents the entire information extracted, through QR scan, from the
 * certification to validate. This information is the [person] owner of the certification, her/his
 * [dateOfBirth], eventual [vaccinations], [tests], or [recoveryStatements] to which was subjected,
 * if the certification [isValid] and if [isCborDecoded].
 *
 */
data class CertificateModel(
    val person: PersonModel?,
    val dateOfBirth: String?,
    val vaccinations: List<VaccinationModel>?,
    val tests: List<TestModel>?,
    val recoveryStatements: List<RecoveryModel>?,
    val isValid: Boolean,
    val isCborDecoded: Boolean,
    var isRevoked: Boolean = false,
    var exemptions: List<Exemption>? = null,
    var isBlackListed: Boolean = false,
    var scanMode: String = "",
    var certificateIdentifier: String = "",
    var certificate: Certificate? = null
)

data class PersonModel(
    val standardisedFamilyName: String = "",
    val familyName: String = "",
    val standardisedGivenName: String = "",
    val givenName: String = ""
)

data class VaccinationModel(
    override val disease: String,
    val vaccine: String,
    val medicinalProduct: String,
    val manufacturer: String,
    val doseNumber: Int,
    val totalSeriesOfDoses: Int,
    val dateOfVaccination: String,
    val countryOfVaccination: String,
    val certificateIssuer: String,
    val certificateIdentifier: String
) : CertificateData

data class TestModel(
    override val disease: String,
    val typeOfTest: String,
    val testName: String?,
    val testNameAndManufacturer: String?,
    val dateTimeOfCollection: String,
    val dateTimeOfTestResult: String?,
    val testResult: String,
    val testingCentre: String,
    val countryOfVaccination: String,
    val certificateIssuer: String,
    val certificateIdentifier: String,
    val resultType: TestResult
) : CertificateData

enum class TestResult(val value: String) {
    DETECTED("DETECTED"),
    NOT_DETECTED("NOT DETECTED")
}

enum class TestType(val value: String) {
    RAPID("LP217198-3"),
    MOLECULAR("LP6464-4")
}

enum class Country(val value: String) {
    IT("IT"),
}

enum class CertCode(val value: String) {
    OID_RECOVERY("1.3.6.1.4.1.1847.2021.1.3"),
    OID_ALT_RECOVERY("1.3.6.1.4.1.0.1847.2021.1.3"),
}

data class RecoveryModel(
    override val disease: String,
    val dateOfFirstPositiveTest: String,
    val countryOfVaccination: String,
    val certificateIssuer: String,
    val certificateValidFrom: String,
    val certificateValidUntil: String,
    val certificateIdentifier: String
) : CertificateData

interface CertificateData {
    val disease: String
}