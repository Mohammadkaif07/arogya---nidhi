package com.example.arogyanidhi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arogyanidhi.domain.model.EligibilityResult
import com.example.arogyanidhi.domain.model.Scheme
import com.example.arogyanidhi.presentation.AppViewModel
import com.example.arogyanidhi.ui.theme.AccentMint
import com.example.arogyanidhi.ui.theme.AccentOrangeSoft

private data class UiStrings(
    val welcomeTitle: String,
    val welcomeSubtitle: String,
    val welcomeHint: String,
    val continueText: String,
    val appTitle: String,
    val appTagline: String,
    val language: String,
    val getStarted: String,
    val checkEligibility: String,
    val checkEligibilitySubtitle: String,
    val findHospitals: String,
    val findHospitalsSubtitle: String,
    val aboutSchemes: String,
    val aboutSchemesSubtitle: String,
    val help: String,
    val helpSubtitle: String,
    val open: String,
    val majorSchemes: String,
    val majorSchemesSubtitle: String,
    val benefitPrefix: String,
    val back: String,
    val step: String,
    val of: String,
    val qIncome: String,
    val qBpl: String,
    val qOccupation: String,
    val qFamilySize: String,
    val qStateDistrict: String,
    val seeResults: String,
    val next: String,
    val dontKnow: String,
    val selectedPrefix: String,
    val occupationLabel: String,
    val stateLabel: String,
    val districtLabel: String,
    val resultsTitlePrefix: String,
    val resultsTitleSuffix: String,
    val resultsSubtitle: String,
    val notMatched: String,
    val backToHome: String,
    val viewDetails: String,
    val schemeDetailsSubtitle: String,
    val benefitsLabel: String,
    val docsNeeded: String,
    val findHospital: String,
    val empaneledHospitals: String,
    val acceptedSchemes: String,
    val callPrefix: String,
    val noOptions: String
)

private fun stringsFor(language: String): UiStrings {
    if (language == "Kannada") {
        return UiStrings(
            welcomeTitle = "ಆರೋಗ್ಯ ನಿಧಿಗೆ ಸ್ವಾಗತ",
            welcomeSubtitle = "ಅರ್ಹತೆ ಪರಿಶೀಲಿಸಿ, ದಾಖಲೆಗಳನ್ನು ಸಿದ್ಧಪಡಿಸಿ ಮತ್ತು ಸಮೀಪದ ಆಸ್ಪತ್ರೆಗಳನ್ನು ಕಂಡುಹಿಡಿಯಿರಿ.",
            welcomeHint = "ನಿಮ್ಮ 5 ಹಂತಗಳ ಅರ್ಹತಾ ಪರಿಶೀಲನೆಯನ್ನು ಪ್ರಾರಂಭಿಸಲು ಮುಂದುವರಿಸಿ ಒತ್ತಿರಿ.",
            continueText = "ಮುಂದುವರಿಸಿ",
            appTitle = "ಆರೋಗ್ಯ ನಿಧಿ",
            appTagline = "ಸರ್ಕಾರಿ ಆರೋಗ್ಯ ಯೋಜನೆಗಳಿಗೆ ನಿಮ್ಮ ಮಾರ್ಗದರ್ಶಿ",
            language = "ಭಾಷೆ",
            getStarted = "ಪ್ರಾರಂಭಿಸಿ",
            checkEligibility = "ನನ್ನ ಅರ್ಹತೆ ಪರಿಶೀಲಿಸಿ",
            checkEligibilitySubtitle = "5 ಸರಳ ಪ್ರಶ್ನೆಗಳಿಗೆ ಉತ್ತರಿಸಿ",
            findHospitals = "ಆಸ್ಪತ್ರೆಗಳನ್ನು ಹುಡುಕಿ",
            findHospitalsSubtitle = "ಜಿಲ್ಲೆ ಆಧಾರವಾಗಿ ಪಟ್ಟಿ ನೋಡಿ",
            aboutSchemes = "ಆರೋಗ್ಯ ಯೋಜನೆಗಳ ಬಗ್ಗೆ",
            aboutSchemesSubtitle = "ಪ್ರಮುಖ ಲಾಭಗಳನ್ನು ತಿಳಿಯಿರಿ",
            help = "ಸಹಾಯ",
            helpSubtitle = "ಆ್ಯಪ್ ಬಳಸಲು ಬೆಂಬಲ ಪಡೆಯಿರಿ",
            open = "ತೆರೆಯಿರಿ",
            majorSchemes = "ಪ್ರಮುಖ ಆರೋಗ್ಯ ಯೋಜನೆಗಳು",
            majorSchemesSubtitle = "ಕರ್ನಾಟಕ ಮತ್ತು ರಾಷ್ಟ್ರೀಯ ಯೋಜನೆಗಳ ಲಾಭಗಳು",
            benefitPrefix = "ಲಾಭ",
            back = "ಹಿಂದೆ",
            step = "ಹಂತ",
            of = "ರಲ್ಲಿ",
            qIncome = "ನಿಮ್ಮ ಕುಟುಂಬದ ವಾರ್ಷಿಕ ಆದಾಯ ಎಷ್ಟು?",
            qBpl = "ನಿಮ್ಮ ಬಳಿ BPL ಅಥವಾ ಅಂತ್ಯೋದಯ ಕಾರ್ಡ್ ಇದೆಯೇ?",
            qOccupation = "ಕುಟುಂಬ ಮುಖ್ಯಸ್ಥರ ಪ್ರಮುಖ ಉದ್ಯೋಗ",
            qFamilySize = "ಕುಟುಂಬದ ಸದಸ್ಯರ ಸಂಖ್ಯೆ ಎಷ್ಟು?",
            qStateDistrict = "ನಿಮ್ಮ ರಾಜ್ಯ ಮತ್ತು ಜಿಲ್ಲೆ ಆಯ್ಕೆಮಾಡಿ",
            seeResults = "ಫಲಿತಾಂಶ ನೋಡಿ",
            next = "ಮುಂದೆ",
            dontKnow = "ಗೊತ್ತಿಲ್ಲ",
            selectedPrefix = "ಆಯ್ಕೆ ಮಾಡಿದುದು",
            occupationLabel = "ಉದ್ಯೋಗ",
            stateLabel = "ರಾಜ್ಯ",
            districtLabel = "ಜಿಲ್ಲೆ",
            resultsTitlePrefix = "ನೀವು",
            resultsTitleSuffix = "ಯೋಜನೆಗಳಿಗೆ ಅರ್ಹರಾಗಿರಬಹುದು",
            resultsSubtitle = "ನಿಮ್ಮ ಕುಟುಂಬ ಪ್ರೊಫೈಲ್ ಆಧರಿಸಿದ ಹೊಂದಾಣಿಕೆ",
            notMatched = "ಹೊಂದಿಕೆಯಾಗದವು",
            backToHome = "ಮುಖಪುಟಕ್ಕೆ ಹಿಂತಿರುಗಿ",
            viewDetails = "ವಿವರಗಳು ನೋಡಿ",
            schemeDetailsSubtitle = "ಯೋಜನೆ ವಿವರಗಳು ಮತ್ತು ಪರಿಶೀಲನಾ ಪಟ್ಟಿ",
            benefitsLabel = "ಲಾಭಗಳು",
            docsNeeded = "ಬೇಕಾಗುವ ದಾಖಲೆಗಳು",
            findHospital = "ಆಸ್ಪತ್ರೆ ಹುಡುಕಿ",
            empaneledHospitals = "ಅಂಗೀಕೃತ ಆಸ್ಪತ್ರೆಗಳು",
            acceptedSchemes = "ಅಂಗೀಕೃತ ಯೋಜನೆಗಳು",
            callPrefix = "ಕರೆ",
            noOptions = "ಆಯ್ಕೆಗಳು ಲಭ್ಯವಿಲ್ಲ"
        )
    }
    return UiStrings(
        welcomeTitle = "Welcome to Arogya Nidhi",
        welcomeSubtitle = "Check eligibility, prepare documents, and find hospitals nearby.",
        welcomeHint = "Tap Continue to start your 5-step eligibility check.",
        continueText = "Continue",
        appTitle = "Arogya Nidhi",
        appTagline = "Your guide to government health schemes",
        language = "Language",
        getStarted = "Get Started",
        checkEligibility = "Check My Eligibility",
        checkEligibilitySubtitle = "Answer 5 simple questions",
        findHospitals = "Find Hospitals",
        findHospitalsSubtitle = "Browse empaneled hospitals by district",
        aboutSchemes = "About Health Schemes",
        aboutSchemesSubtitle = "Learn key benefits",
        help = "Help",
        helpSubtitle = "Get support using the app",
        open = "Open",
        majorSchemes = "Major Health Schemes",
        majorSchemesSubtitle = "Karnataka and national schemes with key benefits",
        benefitPrefix = "Benefit",
        back = "Back",
        step = "Step",
        of = "of",
        qIncome = "What is your family's annual income?",
        qBpl = "Do you have a BPL or Antyodaya card?",
        qOccupation = "Primary occupation of family head",
        qFamilySize = "How many family members are there?",
        qStateDistrict = "Select your state and district",
        seeResults = "See Results",
        next = "Next",
        dontKnow = "Don't Know",
        selectedPrefix = "Selected",
        occupationLabel = "Occupation",
        stateLabel = "State",
        districtLabel = "District",
        resultsTitlePrefix = "You may be eligible for",
        resultsTitleSuffix = "schemes",
        resultsSubtitle = "Matched based on your family profile",
        notMatched = "Not matched",
        backToHome = "Back to Home",
        viewDetails = "View Details",
        schemeDetailsSubtitle = "Scheme details and checklist",
        benefitsLabel = "Benefits",
        docsNeeded = "Documents you'll need",
        findHospital = "Find Hospital",
        empaneledHospitals = "Empaneled Hospitals",
        acceptedSchemes = "Accepted schemes",
        callPrefix = "Call",
        noOptions = "No options available"
    )
}

@Composable
fun ArogyaNidhiApp(vm: AppViewModel = viewModel()) {
    val s = stringsFor(vm.language)
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                s = s,
                onContinue = {
                    val nextRoute = if (vm.onboardingDone) "home" else "onboarding"
                    navController.navigate(nextRoute) {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable("onboarding") {
            OnboardingScreen(
                s = s,
                selectedLanguage = vm.language,
                onLanguageSelected = vm::updateLanguage,
                onGetStarted = {
                    vm.finishOnboarding()
                    navController.navigate("home") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        composable("home") {
            HomeScreen(
                s = s,
                onCheckEligibility = {
                    navController.navigate("quiz")
                },
                onFindHospitals = {
                    navController.navigate("hospitals")
                },
                onAboutSchemes = { navController.navigate("about_schemes") },
                onHelp = { navController.navigate("help") }
            )
        }
        composable("about_schemes") {
            AboutSchemesScreen(
                s = s,
                schemes = vm.schemes,
                onBack = { navController.popBackStack() }
            )
        }
        composable("help") {
            HelpScreen(
                s = s,
                onBack = { navController.popBackStack() }
            )
        }
        composable("quiz") {
            QuizScreen(
                s = s,
                vm = vm,
                onBackToHome = { navController.popBackStack() },
                onComplete = {
                    vm.submitQuiz()
                    navController.navigate("results")
                }
            )
        }
        composable("results") {
            ResultsScreen(
                s = s,
                results = vm.eligibilityResults,
                onSchemeClick = { schemeId ->
                    navController.navigate("scheme/$schemeId")
                },
                onFindHospitals = { navController.navigate("hospitals") },
                onBackHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = "scheme/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id").orEmpty()
            val scheme = vm.getSchemeById(id)
            if (scheme != null) {
                SchemeDetailScreen(
                    s = s,
                    scheme = scheme,
                    checkedDocuments = vm.documentChecks.toSet(),
                    onToggleDocument = vm::toggleDocument,
                    onFindHospital = { navController.navigate("hospitals") },
                    onBack = { navController.popBackStack() }
                )
            }
        }
        composable("hospitals") {
            HospitalScreen(
                s = s,
                vm = vm,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun WelcomeScreen(s: UiStrings, onContinue: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            HeaderCard(
                title = s.welcomeTitle,
                subtitle = s.welcomeSubtitle
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = AccentOrangeSoft)
            ) {
                Text(
                    text = s.welcomeHint,
                    modifier = Modifier.padding(14.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onContinue,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(s.continueText)
            }
        }
    }
}

@Composable
private fun HeaderCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = AccentMint),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun OnboardingScreen(
    s: UiStrings,
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    onGetStarted: () -> Unit
) {
    val languages = listOf("English", "Kannada")
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            HeaderCard(
                title = s.appTitle,
                subtitle = s.appTagline
            )
            Spacer(modifier = Modifier.height(24.dp))
            DropdownPicker(
                label = s.language,
                selected = selectedLanguage,
                options = languages,
                onSelected = onLanguageSelected,
                noOptionsText = s.noOptions
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onGetStarted,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(s.getStarted)
            }
        }
    }
}

@Composable
private fun HomeScreen(
    s: UiStrings,
    onCheckEligibility: () -> Unit,
    onFindHospitals: () -> Unit,
    onAboutSchemes: () -> Unit,
    onHelp: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            HeaderCard(s.appTitle, s.appTagline)
            ActionCard(
                s = s,
                title = s.checkEligibility,
                subtitle = s.checkEligibilitySubtitle,
                action = onCheckEligibility
            )
            ActionCard(
                s = s,
                title = s.findHospitals,
                subtitle = s.findHospitalsSubtitle,
                action = onFindHospitals,
                highlighted = false
            )
            ActionCard(
                s = s,
                title = s.aboutSchemes,
                subtitle = s.aboutSchemesSubtitle,
                action = onAboutSchemes,
                highlighted = false
            )
            ActionCard(
                s = s,
                title = s.help,
                subtitle = s.helpSubtitle,
                action = onHelp,
                highlighted = false
            )
        }
    }
}

@Composable
private fun HelpScreen(s: UiStrings, onBack: () -> Unit) {
    val uriHandler = LocalUriHandler.current
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderCard(
                title = s.help,
                subtitle = "Contact and support information"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text("Contact Us", fontWeight = FontWeight.Bold)
                    Text(
                        "Department of Health & Family Welfare Services\n" +
                            "Arogya Soudha, Magadi Road,\n" +
                            "Bengaluru, Karnataka\n" +
                            "PIN Code: 560023"
                    )
                    HorizontalDivider()
                    Text("Phone", fontWeight = FontWeight.Bold)
                    TextButton(onClick = { uriHandler.openUri("tel:104") }) {
                        Text("Toll Free No: 104")
                    }
                    TextButton(onClick = { uriHandler.openUri("tel:18004258330") }) {
                        Text("Help Line: 1800 425 8330")
                    }
                    HorizontalDivider()
                    Text("Email Us", fontWeight = FontWeight.Bold)
                    TextButton(onClick = { uriHandler.openUri("mailto:jdark-hfws@karnataka.gov.in") }) {
                        Text("jdark-hfws@karnataka.gov.in")
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text(s.back)
            }
        }
    }
}

@Composable
private fun AboutSchemesScreen(s: UiStrings, schemes: List<Scheme>, onBack: () -> Unit) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderCard(
                title = s.majorSchemes,
                subtitle = s.majorSchemesSubtitle
            )
            Spacer(modifier = Modifier.height(12.dp))
            schemes.forEach { scheme ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(scheme.name, fontWeight = FontWeight.Bold)
                        Text(scheme.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("${s.benefitPrefix}: ${scheme.benefits}")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text(s.back)
            }
        }
    }
}

@Composable
private fun ActionCard(
    s: UiStrings,
    title: String,
    subtitle: String,
    action: () -> Unit,
    highlighted: Boolean = true
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (highlighted) AccentOrangeSoft else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Button(onClick = action, shape = RoundedCornerShape(12.dp)) { Text(s.open) }
        }
    }
}

@Composable
private fun QuizScreen(
    s: UiStrings,
    vm: AppViewModel,
    onBackToHome: () -> Unit,
    onComplete: () -> Unit
) {
    val steps = 5
    val question = when (vm.currentQuizStep) {
        1 -> s.qIncome
        2 -> s.qBpl
        3 -> s.qOccupation
        4 -> s.qFamilySize
        5 -> s.qStateDistrict
        else -> ""
    }
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            HeaderCard(
                title = "${s.step} ${vm.currentQuizStep} ${s.of} $steps",
                subtitle = question
            )
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    when (vm.currentQuizStep) {
                        1 -> IncomeStep(vm)
                        2 -> BplStep(vm)
                        3 -> OccupationStep(vm)
                        4 -> FamilySizeStep(vm)
                        5 -> StateDistrictStep(vm)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                if (vm.currentQuizStep == 1) {
                    OutlinedButton(onClick = onBackToHome) { Text(s.back) }
                } else {
                    OutlinedButton(onClick = vm::previousQuizStep) { Text(s.back) }
                }
                Button(
                    onClick = {
                        if (vm.currentQuizStep == steps) onComplete() else vm.nextQuizStep()
                    },
                    enabled = vm.canProceedCurrentStep()
                ) {
                    Text(if (vm.currentQuizStep == steps) s.seeResults else s.next)
                }
            }
        }
    }
}

@Composable
private fun IncomeStep(vm: AppViewModel, s: UiStrings = stringsFor(vm.language)) {
    val brackets = listOf(
        "Below Rs 1 lakh" to 100000,
        "Rs 1 lakh - Rs 2.5 lakh" to 250000,
        "Rs 2.5 lakh - Rs 5 lakh" to 500000,
        "Above Rs 5 lakh" to 700000
    )
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        brackets.forEach { (label, amount) ->
            OutlinedButton(
                onClick = { vm.updateAnnualIncome(amount) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(if (vm.annualIncome == amount) "${s.selectedPrefix}: $label" else label)
            }
        }
    }
}

@Composable
private fun BplStep(vm: AppViewModel, s: UiStrings = stringsFor(vm.language)) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(onClick = { vm.updateHasBpl(true) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
            Text(if (vm.hasBpl == true) "${s.selectedPrefix}: Yes" else "Yes")
        }
        OutlinedButton(onClick = { vm.updateHasBpl(false) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
            Text(if (vm.hasBpl == false) "${s.selectedPrefix}: No" else "No")
        }
        OutlinedButton(onClick = { vm.updateHasBpl(null) }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp)) {
            Text(s.dontKnow)
        }
    }
}

@Composable
private fun OccupationStep(vm: AppViewModel, s: UiStrings = stringsFor(vm.language)) {
    val occupations = listOf("Farmer", "Laborer", "Self-employed", "Salaried", "Other")
    DropdownPicker(
        label = s.occupationLabel,
        selected = vm.occupation,
        options = occupations,
        onSelected = vm::updateOccupation,
        noOptionsText = s.noOptions
    )
}

@Composable
private fun FamilySizeStep(vm: AppViewModel) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedButton(onClick = { vm.updateFamilySize(vm.familySize - 1) }) { Text("-") }
        Text(vm.familySize.toString(), style = MaterialTheme.typography.headlineMedium)
        OutlinedButton(onClick = { vm.updateFamilySize(vm.familySize + 1) }) { Text("+") }
    }
}

@Composable
private fun StateDistrictStep(vm: AppViewModel, s: UiStrings = stringsFor(vm.language)) {
    val states = vm.statesWithDistricts.keys.toList()
    val districts = vm.statesWithDistricts[vm.selectedState].orEmpty()
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        DropdownPicker(
            label = s.stateLabel,
            selected = vm.selectedState,
            options = states,
            onSelected = vm::setState,
            noOptionsText = s.noOptions
        )
        DropdownPicker(
            label = s.districtLabel,
            selected = vm.selectedDistrict,
            options = districts,
            onSelected = vm::setDistrict,
            noOptionsText = s.noOptions
        )
    }
}

@Composable
private fun ResultsScreen(
    s: UiStrings,
    results: List<EligibilityResult>,
    onSchemeClick: (String) -> Unit,
    onFindHospitals: () -> Unit,
    onBackHome: () -> Unit
) {
    val eligible = results.filter { it.isEligible }
    val notEligible = results.filterNot { it.isEligible }
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderCard(
                title = "${s.resultsTitlePrefix} ${eligible.size} ${s.resultsTitleSuffix}",
                subtitle = s.resultsSubtitle
            )
            Spacer(modifier = Modifier.height(12.dp))
            eligible.forEach { result ->
                SchemeCard(s = s, result = result, onSchemeClick = onSchemeClick)
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (notEligible.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(s.notMatched, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                notEligible.forEach { result ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(result.scheme.name, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(result.failureReasons.joinToString(), color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onFindHospitals, modifier = Modifier.fillMaxWidth()) {
                Text(s.findHospitals)
            }
            TextButton(onClick = onBackHome, modifier = Modifier.fillMaxWidth()) {
                Text(s.backToHome)
            }
        }
    }
}

@Composable
private fun SchemeCard(s: UiStrings, result: EligibilityResult, onSchemeClick: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AccentOrangeSoft)
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(result.scheme.name, fontWeight = FontWeight.Bold)
            Text(result.scheme.benefits)
            TextButton(onClick = { onSchemeClick(result.scheme.id) }) {
                Text(s.viewDetails)
            }
        }
    }
}

@Composable
private fun SchemeDetailScreen(
    s: UiStrings,
    scheme: Scheme,
    checkedDocuments: Set<String>,
    onToggleDocument: (String) -> Unit,
    onFindHospital: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderCard(scheme.name, s.schemeDetailsSubtitle)
            Spacer(modifier = Modifier.height(8.dp))
            Text(scheme.description)
            Spacer(modifier = Modifier.height(8.dp))
            Text("${s.benefitsLabel}: ${scheme.benefits}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(s.docsNeeded, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            scheme.documents.forEach { doc ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedDocuments.contains(doc),
                        onCheckedChange = { onToggleDocument(doc) }
                    )
                    Text(doc)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onFindHospital, modifier = Modifier.fillMaxWidth()) {
                Text(s.findHospital)
            }
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text(s.back)
            }
        }
    }
}

@Composable
private fun HospitalScreen(s: UiStrings, vm: AppViewModel, onBack: () -> Unit) {
    val hospitals = vm.hospitalsForCurrentSelection()
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(s.empaneledHospitals, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            StateDistrictStep(vm = vm)
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                hospitals.forEach { hospital ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = AccentMint)
                    ) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(hospital.name, fontWeight = FontWeight.Bold)
                            Text(hospital.address)
                            Text("${hospital.district}, ${hospital.state}")
                            Text("${s.acceptedSchemes}: ${hospital.schemesAccepted.joinToString()}")
                            hospital.phone?.let { Text("${s.callPrefix}: $it") }
                        }
                    }
                }
            }
            TextButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text(s.back)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DropdownPicker(
    label: String,
    selected: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    noOptionsText: String = "No options available"
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selected,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
    if (options.isEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.width(4.dp))
            Text(noOptionsText, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
