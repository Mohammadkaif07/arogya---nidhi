package com.example.arogyanidhi.data.repository

import com.example.arogyanidhi.domain.model.Hospital
import com.example.arogyanidhi.domain.model.Scheme

class LocalDataRepository {
    fun getSchemes(): List<Scheme> = listOf(
        Scheme(
            id = "pmjay",
            name = "Ayushman Bharat (PM-JAY)",
            description = "National health assurance program for vulnerable families.",
            benefits = "Free treatment up to Rs 5 lakh per family per year",
            maxIncome = 500000,
            requiresBpl = null,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("Aadhaar Card", "Ration Card", "Income Certificate")
        ),
        Scheme(
            id = "rsby",
            name = "Rashtriya Swasthya Bima Yojana",
            description = "Health insurance support for BPL households.",
            benefits = "Coverage up to Rs 30,000 per family per year",
            maxIncome = null,
            requiresBpl = true,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("BPL Certificate", "Aadhaar Card", "Family ID")
        ),
        Scheme(
            id = "vajpayee_arogyashree",
            name = "Vajpayee Arogyashree Scheme",
            description = "Cashless tertiary care support for BPL families in Karnataka, including pre-existing conditions at empaneled hospitals.",
            benefits = "Up to Rs 1.5 lakh per family annually with additional Rs 50,000 support",
            maxIncome = null,
            requiresBpl = true,
            validOccupations = null,
            validStates = listOf("Karnataka"),
            documents = listOf("BPL Card", "Aadhaar Card", "Karnataka Residence Proof")
        ),
        Scheme(
            id = "rajiv_arogya_bhagya",
            name = "Rajiv Arogya Bhagya Scheme",
            description = "Financial support for major surgeries for Karnataka APL families across heart, cancer, neuro and pediatric specialties.",
            benefits = "Coverage support for 663 surgical procedures under 7 specialty areas",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = listOf("Karnataka"),
            documents = listOf("APL Card", "Aadhaar Card", "State ID")
        ),
        Scheme(
            id = "jyothi_sanjeevini",
            name = "Jyothi Sanjeevini Scheme",
            description = "Healthcare assistance for Karnataka government employees and family members.",
            benefits = "Coverage for 449 procedures with major government cost-sharing for hospital admissions",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = listOf("Salaried"),
            validStates = listOf("Karnataka"),
            documents = listOf("Government Employee ID", "Aadhaar Card", "Family Details")
        ),
        Scheme(
            id = "janani_suraksha_yojana",
            name = "Janani Suraksha Yojana (JSY)",
            description = "Maternal and infant health support for institutional deliveries and postpartum care.",
            benefits = "Financial incentives for hospital delivery, C-section support and post-delivery planning services",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("Pregnancy Record", "Aadhaar Card", "Bank Account Details")
        ),
        Scheme(
            id = "mission_indradhanush",
            name = "Mission Indradhanush",
            description = "Full immunization support for unvaccinated and partially vaccinated children under age 5.",
            benefits = "Covers key childhood vaccines including Polio, DPT, Measles, TB and Hepatitis B",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("Child Immunization Card", "Birth Certificate", "Parent ID")
        ),
        Scheme(
            id = "rbsk",
            name = "Rashtriya Bala Swasthya Karyakrama (RBSK)",
            description = "Early screening and intervention program for children aged 0-18 years.",
            benefits = "Free screening and treatment support for identified conditions in children",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("Child Health Card", "Birth Certificate", "Parent ID")
        ),
        Scheme(
            id = "npcbvi",
            name = "National Programme for Control of Blindness and Visual Impairment",
            description = "Program to reduce avoidable blindness and expand accessible eye care services.",
            benefits = "Free cataract surgeries, vision screening and corrective support",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = emptyList(),
            documents = listOf("Aadhaar Card", "Medical Referral", "Health Records")
        ),
        Scheme(
            id = "prasooti_araike",
            name = "Prasooti Araike Scheme",
            description = "Maternal nutrition and financial support program for pregnant and lactating women.",
            benefits = "Financial support for antenatal care and additional support for eligible mothers",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = listOf("Karnataka"),
            documents = listOf("Pregnancy Record", "Aadhaar Card", "Bank Passbook")
        ),
        Scheme(
            id = "madilu_kit",
            name = "Karnataka Madilu Kit Scheme",
            description = "Postnatal support scheme providing newborn and maternal care essentials after institutional delivery.",
            benefits = "Free mother-and-baby care kit with 19 components worth around Rs 1,500",
            maxIncome = null,
            requiresBpl = null,
            validOccupations = null,
            validStates = listOf("Karnataka"),
            documents = listOf("Delivery Discharge Summary", "Mother ID Proof", "Hospital Registration")
        )
    )

    fun getHospitals(): List<Hospital> = listOf(
        Hospital(
            id = "h5",
            name = "Bahmani Hospital",
            address = "Khonialawa, Mominpura, Kalaburagi, Karnataka 585104",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472221825",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h6",
            name = "Banale Hospital",
            address = "Ganesh Market, Khuba Plot, Brahmpur, Kalaburagi, Karnataka 585105",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "09663418290",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h7",
            name = "Basaveshwara Hospital",
            address = "Basaveshwar Teaching and General Hospital, Rajapur, Sedam Road, Kalaburagi 585105",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472247955",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h8",
            name = "Chirayu Hospital Kalaburgi",
            address = "Court Road, Opp Gescom Office, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472241717",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h9",
            name = "Dhanvantri Hospital",
            address = "New Jewargi Rd, Kotnoor, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "09980759148",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h10",
            name = "District Government Hospital",
            address = "Kuvempu Nagar, Kalaburagi, Karnataka 585105",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472278644",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h11",
            name = "ESIC Hospital, Gulbarga",
            address = "Gulbarga University, Jnana Ganga, Kalnoor, Kalaburagi, Karnataka 585106",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472265548",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h12",
            name = "Gulbarga Heart Foundation and Research Centre",
            address = "STBT Cross, Kalaburagi, Karnataka 585101",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472689191",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h13",
            name = "HCG Cancer Center, Gulbarga",
            address = "No. 1-10/A, 1-10, Khuba Plot, Brahmpur, Kalaburagi, Karnataka 585105",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472661000",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h14",
            name = "Homeocare International Homeopathy Clinic Gulbarga",
            address = "1st Floor, Kandoor Shopping Mall, Timmapuri Chowk, Station Main Road, Kalaburagi 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "9550003388",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h15",
            name = "Kamareddy Hospital",
            address = "No. 9, Vasanth Nagar, Opp Govt ITI College, Bus Stand Road, Kalaburagi 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472256163",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h16",
            name = "Medicare Multispeciality Hospital",
            address = "Darga Rd, Bapu Nagar, Maktampura, Kalaburagi, Karnataka 585101",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "09141363388",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h17",
            name = "Nishty Heart Centre",
            address = "No. 15 and 16, Lahoti Enclave, Aiwan-E-Shahi Road, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472232596",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h18",
            name = "Rudrawadi Hospital",
            address = "Old Jewargi Rd, Balaji Nagar, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "09972222616",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h19",
            name = "Sangameshwar Hospital",
            address = "M.S.K. Mill Road, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472222435",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h20",
            name = "Sri Jayadeva Institute of Cardiovascular Sciences and Research",
            address = "GIMS Campus, Sedam Road, Kalaburagi, Karnataka",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472230500",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h21",
            name = "Sunrise Multispeciality Hospital",
            address = "Vasanth Nagar, Nagargum, Kalaburagi, Karnataka 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472272777",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h22",
            name = "United Hospital Gulbarga",
            address = "1-43/A, Opp Siddarth Law College, Court Road, Near SVP Chowk, Kalaburagi 585102",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472225006",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h23",
            name = "Vaatsalya Hospital",
            address = "RTO Circle Road, SH10, Kalaburagi, Karnataka",
            district = "Kalaburagi",
            state = "Karnataka",
            phone = "08472222299",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h24",
            name = "ADAGURU PHC",
            address = "Medical Officer, Primary Health Centre, K.R. Nagara Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h25",
            name = "AIWC (IMA) PHC",
            address = "Medical Officer, Primary Health Centre, Mysuru Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h26",
            name = "ANNUR PHC",
            address = "Medical Officer, Primary Health Centre, H.D. Kote, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h27",
            name = "ANTHARASANTHE MTHU",
            address = "Medical Officer, Primary Health Centre, H.D. Kote, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h28",
            name = "ASHOKAPURAM PHC",
            address = "Medical Officer, Primary Health Centre, Mysuru Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h29",
            name = "ATTIGODU MTHU",
            address = "Medical Officer, Primary Health Centre, Periyapatna Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h30",
            name = "B MATAKERE PHC",
            address = "Medical Officer, Primary Health Centre, H.D. Kote, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h31",
            name = "BADAGALAPURA PHC",
            address = "Medical Officer, Primary Health Centre, H.D. Kote, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h32",
            name = "BANNIKUPPE PHC",
            address = "Medical Officer, Primary Health Centre, Bannikuppe, Hunsur Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = "08222244328",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h33",
            name = "BANNIMANTAPA PHC",
            address = "Medical Officer, Primary Health Centre, Mysuru Taluk, Mysuru, Karnataka",
            district = "Mysuru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h34",
            name = "Bangalore Baptist Hospital",
            address = "Bellary Road, Hebbal, Bengaluru, Karnataka 560024",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-23330323",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h35",
            name = "Bowring Hospital",
            address = "Lady Curzon Road, Shivaji Nagar, Tasker Town, Bengaluru, Karnataka 560001",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-25591362",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h36",
            name = "Central Leprosorium Hospital",
            address = "1st Cross, Magadi Road, Near Binny Mill, Bengaluru, Karnataka 560023",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-23350239",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h37",
            name = "ESI Hospital",
            address = "8/3, E Block, I Block, I Stage, Rajajinagar, Bengaluru, Karnataka 560010",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-23324112",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h38",
            name = "Jayadeva Institute of Cardiology",
            address = "Bannerghatta Main Road, Jayanagara 9th Block, Bengaluru, Karnataka 560069",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-26534600",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h39",
            name = "K.C. General Hospital",
            address = "No. 89, 5th Cross Road, Behind Police Station, Malleswaram, Bengaluru, Karnataka 560003",
            district = "Bengaluru",
            state = "Karnataka",
            phone = null,
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h40",
            name = "KIDWAI",
            address = "Dr M H Mariagowda Road, Near Bangalore Dairy, Bengaluru, Karnataka 560029",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-26094000",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h41",
            name = "Mallige Medical Centre Pvt Ltd",
            address = "No. 31/32, Crescent Road, Near Shivananda Circle, Bengaluru, Karnataka 560001",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-22203333",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h42",
            name = "Mallya Hospital",
            address = "No. 2, Vittal Mallya Road, Ashok Nagar, Bengaluru, Karnataka 560001",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-22277979",
            schemesAccepted = listOf("pmjay")
        ),
        Hospital(
            id = "h43",
            name = "Manipal Hospital",
            address = "98, HAL Airport Road, Bengaluru, Karnataka 560017",
            district = "Bengaluru",
            state = "Karnataka",
            phone = "080-25268901",
            schemesAccepted = listOf("pmjay")
        )
    )

    fun getStatesWithDistricts(): Map<String, List<String>> = mapOf(
        "Karnataka" to listOf("Bengaluru", "Mysuru", "Kalaburagi")
    )
}
