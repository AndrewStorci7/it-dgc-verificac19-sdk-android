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
 */

package it.ministerodellasalute.verificaC19sdk.model

/**
 *
 * This enum class defines all the possible status of certifications after their verification.
 *
 */
enum class CertificateStatus {
    NOT_VALID,
    NOT_VALID_YET,
    VALID,
    REVOKED,
    NOT_EU_DCC,
    TEST_NEEDED;
}

fun CertificateStatus.applyFullModel(fullModel: Boolean): CertificateStatus {
    return if (!fullModel && this == CertificateStatus.NOT_VALID_YET) {
        CertificateStatus.NOT_VALID
    } else this
}