package com.muslimqi.design

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.MenuBook
import androidx.compose.material.icons.rounded.Mosque
import androidx.compose.material.icons.rounded.NotificationsActive
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.RocketLaunch
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.SportsEsports
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DarkColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.graphicsLayer
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.math.roundToInt
import kotlin.random.Random

private val V5Emerald = Color(0xFF119B79)
private val V5EmeraldDark = Color(0xFF075C54)
private val V5Teal = Color(0xFF0B3D45)
private val V5Cream = Color(0xFFF8F4E8)
private val V5Warm = Color(0xFFFFFCF5)
private val V5Gold = Color(0xFFE0B94D)
private val V5Sand = Color(0xFFF0E2BF)
private val V5Blue = Color(0xFF3D7EA3)
private val V5Purple = Color(0xFF8063A8)
private val V5Coral = Color(0xFFDC675D)
private val V5Night = Color(0xFF071B1A)
private val V5NightSurface = Color(0xFF12312E)

private enum class V5Page {
    Splash, Language, Onboarding, Account, Home, Play, Permissions,
    MemorySetup, MemoryGame, Mosques, Progress, Ranking, Profile
}

private data class V5Nav(val page: V5Page, val label: String, val icon: ImageVector)
private val v5Navigation = listOf(
    V5Nav(V5Page.Home, "Accueil", Icons.Rounded.Home),
    V5Nav(V5Page.Play, "Jouer", Icons.Rounded.SportsEsports),
    V5Nav(V5Page.Progress, "Progrès", Icons.Rounded.AutoGraph),
    V5Nav(V5Page.Ranking, "Classement", Icons.Rounded.EmojiEvents),
    V5Nav(V5Page.Profile, "Profil", Icons.Rounded.Person)
)

private data class GridFormat(val columns: Int, val rows: Int, val label: String, val level: String) {
    val cardCount: Int get() = columns * rows
    val pairCount: Int get() = cardCount / 2
}

private val gridFormats = listOf(
    GridFormat(4, 4, "4 × 4", "Classique"),
    GridFormat(4, 5, "4 × 5", "Intermédiaire"),
    GridFormat(4, 6, "4 × 6", "Difficile"),
    GridFormat(4, 7, "4 × 7", "Expert")
)

private data class PairDefinition(val key: String, val arabic: String, val french: String)
private val pairLibrary = listOf(
    PairDefinition("kaaba", "الكعبة", "La Kaaba"),
    PairDefinition("makkah", "مكة", "La Mecque"),
    PairDefinition("madinah", "المدينة", "Médine"),
    PairDefinition("ramadan", "رمضان", "Ramadan"),
    PairDefinition("sabr", "الصبر", "Patience"),
    PairDefinition("ilm", "العلم", "Savoir"),
    PairDefinition("amanah", "الأمانة", "Confiance"),
    PairDefinition("rahma", "الرحمة", "Miséricorde"),
    PairDefinition("fajr", "الفجر", "Fajr"),
    PairDefinition("qibla", "القبلة", "Qibla"),
    PairDefinition("hijra", "الهجرة", "Hégire"),
    PairDefinition("zakat", "الزكاة", "Zakât"),
    PairDefinition("hajj", "الحج", "Hajj"),
    PairDefinition("salam", "السلام", "Paix")
)

private data class MemoryCardModel(val instanceId: Int, val pairId: Int, val definition: PairDefinition)

private data class MosqueResult(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distanceMeters: Int,
    val website: String?
)

private data class PrayerTimesResult(
    val fajr: String,
    val dhuhr: String,
    val asr: String,
    val maghrib: String,
    val isha: String,
    val sourceLabel: String
)

class FunctionalMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        createNotificationChannels()
        val testPage = intent.getStringExtra("test_page")
        setContent { FunctionalMuslimQiApp(testPage) }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannels(
                listOf(
                    NotificationChannel(
                        "prayer_reminders",
                        "Rappels de prière",
                        NotificationManager.IMPORTANCE_HIGH
                    ).apply { description = "Alertes configurées pour les horaires de prière" },
                    NotificationChannel(
                        "education_challenges",
                        "Défis éducatifs",
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply { description = "Défi quotidien, série et progression" }
                )
            )
        }
    }
}

@Composable
private fun FunctionalMuslimQiApp(testPage: String?) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("muslim_qi_v5", Context.MODE_PRIVATE) }
    val initialPage = when (testPage) {
        "memory_setup" -> V5Page.MemorySetup
        "memory_4x7" -> V5Page.MemoryGame
        "mosques" -> V5Page.Mosques
        else -> V5Page.Splash
    }
    var page by rememberSaveable { mutableStateOf(initialPage) }
    var dark by rememberSaveable { mutableStateOf(false) }
    var rtl by rememberSaveable { mutableStateOf(false) }
    var selectedFormatIndex by rememberSaveable { mutableIntStateOf(if (testPage == "memory_4x7") 3 else 0) }
    var permissionIntroDone by rememberSaveable { mutableStateOf(prefs.getBoolean("permission_intro_done", false)) }

    val light = lightColorScheme(
        primary = V5Emerald,
        onPrimary = Color.White,
        primaryContainer = Color(0xFFDDF5EC),
        onPrimaryContainer = V5EmeraldDark,
        secondary = V5Gold,
        onSecondary = Color(0xFF3D2C00),
        background = V5Cream,
        onBackground = Color(0xFF153936),
        surface = V5Warm,
        onSurface = Color(0xFF153936),
        surfaceVariant = Color(0xFFF1EBDE),
        onSurfaceVariant = Color(0xFF667873),
        outline = Color(0xFFD8D5C9)
    )
    val night = darkColorScheme(
        primary = Color(0xFF68D9B8),
        onPrimary = Color(0xFF00382E),
        primaryContainer = Color(0xFF164D43),
        onPrimaryContainer = Color(0xFFC9F8E8),
        secondary = Color(0xFFF3CE68),
        onSecondary = Color(0xFF3C3000),
        background = V5Night,
        onBackground = Color(0xFFF4F8F3),
        surface = V5NightSurface,
        onSurface = Color(0xFFF4F8F3),
        surfaceVariant = Color(0xFF1B3C38),
        onSurfaceVariant = Color(0xFFB8CAC4),
        outline = Color(0xFF3A5751)
    )

    CompositionLocalProvider(LocalLayoutDirection provides if (rtl) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        MaterialTheme(
            colorScheme = if (dark) night else light,
            typography = Typography(
                displaySmall = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 38.sp),
                headlineMedium = androidx.compose.ui.text.TextStyle(fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 28.sp),
                titleLarge = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 22.sp),
                titleMedium = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
                bodyLarge = androidx.compose.ui.text.TextStyle(fontSize = 15.sp),
                bodyMedium = androidx.compose.ui.text.TextStyle(fontSize = 13.sp)
            )
        ) {
            V5Backdrop {
                AnimatedContent(
                    targetState = page,
                    modifier = Modifier.fillMaxSize(),
                    transitionSpec = { fadeIn(tween(220)) togetherWith fadeOut(tween(150)) },
                    label = "v5_page"
                ) { target ->
                    when (target) {
                        V5Page.Splash -> V5Splash { page = V5Page.Language }
                        V5Page.Language -> V5Language({ page = V5Page.Onboarding }) { rtl = it }
                        V5Page.Onboarding -> V5Onboarding { page = V5Page.Account }
                        V5Page.Account -> V5Account { page = V5Page.Home }
                        V5Page.Home -> V5Shell(V5Page.Home, { page = it }) {
                            V5Home(
                                openPlay = { page = V5Page.Play },
                                openMemory = {
                                    page = if (permissionIntroDone) V5Page.MemorySetup else V5Page.Permissions
                                },
                                openMosques = { page = V5Page.Mosques }
                            )
                        }
                        V5Page.Play -> V5Shell(V5Page.Play, { page = it }) {
                            V5Play(
                                openMemory = {
                                    page = if (permissionIntroDone) V5Page.MemorySetup else V5Page.Permissions
                                }
                            )
                        }
                        V5Page.Permissions -> PermissionCenter(
                            onBack = { page = V5Page.Play },
                            onContinue = {
                                permissionIntroDone = true
                                prefs.edit().putBoolean("permission_intro_done", true).apply()
                                page = V5Page.MemorySetup
                            }
                        )
                        V5Page.MemorySetup -> MemorySetupPage(
                            selectedFormatIndex = selectedFormatIndex,
                            onSelected = { selectedFormatIndex = it },
                            onBack = { page = V5Page.Play },
                            onStart = { page = V5Page.MemoryGame }
                        )
                        V5Page.MemoryGame -> MemoryGamePage(
                            format = gridFormats[selectedFormatIndex],
                            onBack = { page = V5Page.MemorySetup }
                        )
                        V5Page.Mosques -> NearbyMosquesPage(onBack = { page = V5Page.Home })
                        V5Page.Progress -> V5Shell(V5Page.Progress, { page = it }) { V5Progress() }
                        V5Page.Ranking -> V5Shell(V5Page.Ranking, { page = it }) { V5Ranking() }
                        V5Page.Profile -> V5Shell(V5Page.Profile, { page = it }) {
                            V5Profile(
                                dark = dark,
                                setDark = { dark = it },
                                rtl = rtl,
                                setRtl = { rtl = it },
                                openPermissions = { page = V5Page.Permissions },
                                openMosques = { page = V5Page.Mosques }
                            )
                        }
                    }
                }
            }
        }
    }

    BackHandler(enabled = page !in listOf(V5Page.Splash, V5Page.Language, V5Page.Home)) {
        page = when (page) {
            V5Page.Onboarding -> V5Page.Language
            V5Page.Account -> V5Page.Onboarding
            V5Page.Permissions, V5Page.MemorySetup -> V5Page.Play
            V5Page.MemoryGame -> V5Page.MemorySetup
            V5Page.Mosques -> V5Page.Home
            else -> V5Page.Home
        }
    }
}

@Composable
private fun V5Backdrop(content: @Composable BoxScope.() -> Unit) {
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Canvas(Modifier.matchParentSize()) {
            val p = MaterialTheme.colorScheme.primary.copy(alpha = .045f)
            val g = V5Gold.copy(alpha = .055f)
            drawCircle(p, size.width * .52f, Offset(size.width * 1.06f, size.height * .05f))
            drawCircle(g, size.width * .42f, Offset(-size.width * .06f, size.height * .90f))
            val step = 74.dp.toPx()
            var x = 0f
            while (x < size.width + step) {
                var y = 0f
                while (y < size.height + step) {
                    val c = Offset(x, y)
                    drawCircle(p, 17.dp.toPx(), c, style = Stroke(1.dp.toPx()))
                    drawLine(p, c + Offset(-9.dp.toPx(), 0f), c + Offset(9.dp.toPx(), 0f), 1.dp.toPx())
                    drawLine(p, c + Offset(0f, -9.dp.toPx()), c + Offset(0f, 9.dp.toPx()), 1.dp.toPx())
                    y += step
                }
                x += step
            }
        }
        content()
    }
}

@Composable
private fun V5Logo(size: Dp) {
    Canvas(Modifier.size(size)) {
        val c = Offset(this.size.width / 2, this.size.height / 2)
        val r = this.size.minDimension * .39f
        rotate(45f, c) {
            drawRoundRect(V5Gold, c - Offset(r, r), Size(r * 2, r * 2), CornerRadius(14f, 14f), style = Stroke(4.5f))
        }
        drawCircle(V5EmeraldDark, r * .84f, c)
        drawCircle(V5Gold, r * .70f, c, style = Stroke(3.7f))
        drawCircle(V5Gold.copy(alpha = .30f), r * .39f, c, style = Stroke(3f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 7f))))
        drawLine(V5Gold, c + Offset(0f, -r * .47f), c + Offset(0f, r * .43f), 4.5f, StrokeCap.Round)
        drawCircle(V5Gold, r * .105f, c + Offset(0f, -r * .47f))
    }
}

@Composable
private fun V5Splash(done: () -> Unit) {
    LaunchedEffect(Unit) { delay(1100); done() }
    Box(
        Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(V5EmeraldDark, V5Teal, Color(0xFF082B2C)))),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            V5Logo(120.dp)
            Text("Muslim QI", color = Color.White, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold, fontSize = 42.sp, modifier = Modifier.padding(top = 24.dp))
            Text("Apprends l’islam chaque jour en t’amusant.", color = Color.White.copy(alpha = .74f), fontSize = 13.sp)
            LinearProgressIndicator(
                progress = { .74f },
                modifier = Modifier.padding(top = 54.dp).width(88.dp).height(4.dp).clip(CircleShape),
                color = V5Gold,
                trackColor = Color.White.copy(alpha = .12f)
            )
        }
    }
}

@Composable
private fun V5IntroLayout(bottom: @Composable (() -> Unit)? = null, content: @Composable ColumnScope.() -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            if (bottom != null) Surface(color = MaterialTheme.colorScheme.background.copy(alpha = .97f)) {
                Box(Modifier.navigationBarsPadding().padding(horizontal = 22.dp, vertical = 14.dp)) { bottom() }
            }
        }
    ) { pad ->
        Column(
            Modifier.fillMaxSize().padding(pad).padding(horizontal = 22.dp).statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content
        )
    }
}

@Composable
private fun V5Language(next: () -> Unit, setRtl: (Boolean) -> Unit) {
    var selected by rememberSaveable { mutableIntStateOf(0) }
    V5IntroLayout(bottom = { V5MainButton("Continuer", Icons.Rounded.ArrowForward, next) }) {
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("MUSLIM QI", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, letterSpacing = 1.2.sp)
            Text("1 / 3", color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        LinearProgressIndicator(progress = { 1f / 3f }, modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            V5Logo(70.dp)
            Text("Choisissez votre langue", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 14.dp))
            Text("Une expérience fluide en français, en arabe ou en anglais.", color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 7.dp))
        }
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            V5LanguageCard("Français", "Interface complète en français", "FR", selected == 0) { selected = 0; setRtl(false) }
            V5LanguageCard("العربية", "واجهة عربية كاملة من اليمين إلى اليسار", "AR", selected == 1) { selected = 1; setRtl(true) }
            V5LanguageCard("English", "Complete English interface", "EN", selected == 2) { selected = 2; setRtl(false) }
        }
        V5InfoStrip(Icons.Rounded.Translate, "Arabe RTL intégral", "Navigation, textes et cartes s’adaptent automatiquement.")
    }
}

@Composable
private fun V5LanguageCard(title: String, subtitle: String, code: String, selected: Boolean, click: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth().clickable(onClick = click).border(1.5.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        shadowElevation = if (selected) 9.dp else 2.dp
    ) {
        Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(50.dp).clip(RoundedCornerShape(16.dp)).background(if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant), contentAlignment = Alignment.Center) {
                Text(code, color = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.ExtraBold)
            }
            Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
            }
            Icon(if (selected) Icons.Rounded.CheckCircle else Icons.Rounded.ChevronRight, null, tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun V5Onboarding(done: () -> Unit) {
    var slide by rememberSaveable { mutableIntStateOf(0) }
    val titles = listOf("Apprenez avec confiance", "Jouez selon votre rythme", "Progressez chaque jour")
    val bodies = listOf(
        "Des explications courtes, structurées et accompagnées de références éditoriales.",
        "Quatre formats de Memory, du 4 × 4 au 4 × 7, entièrement adaptés à l’écran.",
        "Objectifs, badges et thèmes à revoir, sans pression ni jugement."
    )
    val icons = listOf(Icons.Rounded.MenuBook, Icons.Rounded.GridView, Icons.Rounded.AutoGraph)
    V5IntroLayout(bottom = {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(3) { index ->
                    Box(Modifier.padding(4.dp).width(if (index == slide) 28.dp else 8.dp).height(8.dp).clip(CircleShape).background(if (index == slide) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline))
                }
            }
            V5MainButton(if (slide == 2) "Commencer" else "Continuer", if (slide == 2) Icons.Rounded.RocketLaunch else Icons.Rounded.ArrowForward) {
                if (slide == 2) done() else slide++
            }
        }
    }) {
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("MUSLIM QI", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            TextButton(onClick = done) { Text("Passer") }
        }
        Surface(Modifier.fillMaxWidth().height(250.dp).shadow(16.dp, RoundedCornerShape(32.dp)), shape = RoundedCornerShape(32.dp), color = V5Teal) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Canvas(Modifier.matchParentSize()) { drawCircle(V5Gold.copy(alpha = .14f), size.minDimension * .67f, Offset(size.width, 0f)) }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.size(108.dp).clip(RoundedCornerShape(32.dp)).background(Color.White.copy(alpha = .10f)), contentAlignment = Alignment.Center) {
                        Icon(icons[slide], null, tint = if (slide == 1) V5Gold else Color.White, modifier = Modifier.size(58.dp))
                    }
                    Text("Muslim QI", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, modifier = Modifier.padding(top = 17.dp))
                    Text("La connaissance, un pas après l’autre", color = Color.White.copy(alpha = .64f), fontSize = 10.sp)
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Text(titles[slide], style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
            Text(bodies[slide], color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, lineHeight = 21.sp, modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
private fun V5Account(done: () -> Unit) {
    V5IntroLayout {
        Spacer(Modifier.height(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            V5Logo(74.dp)
            Text("Comment continuer ?", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 16.dp))
            Text("Commencez immédiatement. La synchronisation sera reliée ensuite.", color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 7.dp))
        }
        V5AccountCard("Invité", "Jouer sans créer de compte", true, done)
        V5AccountCard("Google", "Synchronisation multi-appareils", false, done)
        V5AccountCard("Apple", "Connexion sécurisée", false, done)
        V5AccountCard("E-mail", "Adresse et mot de passe", false, done)
        V5InfoStrip(Icons.Rounded.Security, "Données protégées", "Export et suppression seront disponibles depuis le profil.")
    }
}

@Composable
private fun V5AccountCard(title: String, subtitle: String, strong: Boolean, click: () -> Unit) {
    Surface(
        Modifier.fillMaxWidth().clickable(onClick = click),
        shape = RoundedCornerShape(22.dp),
        color = if (strong) V5Teal else MaterialTheme.colorScheme.surface,
        shadowElevation = if (strong) 10.dp else 3.dp
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(49.dp).clip(RoundedCornerShape(16.dp)).background(if (strong) Color.White.copy(alpha = .12f) else MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) {
                Icon(if (strong) Icons.Rounded.Person else Icons.Rounded.Lock, null, tint = if (strong) Color.White else MaterialTheme.colorScheme.primary)
            }
            Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                Text(title, color = if (strong) Color.White else MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                Text(subtitle, color = if (strong) Color.White.copy(alpha = .66f) else MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = if (strong) V5Gold else MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun V5MainButton(text: String, icon: ImageVector, click: () -> Unit) {
    Button(
        onClick = click,
        modifier = Modifier.fillMaxWidth().height(58.dp).shadow(10.dp, RoundedCornerShape(19.dp)),
        shape = RoundedCornerShape(19.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(text, fontWeight = FontWeight.ExtraBold)
        Icon(icon, null, modifier = Modifier.padding(start = 8.dp).size(20.dp))
    }
}

@Composable
private fun V5InfoStrip(icon: ImageVector, title: String, body: String) {
    Surface(shape = RoundedCornerShape(20.dp), color = V5Gold.copy(alpha = .14f), modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(42.dp).clip(RoundedCornerShape(14.dp)).background(V5Gold.copy(alpha = .18f)), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = Color(0xFF9A7517), modifier = Modifier.size(22.dp))
            }
            Column(Modifier.padding(start = 12.dp)) {
                Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)
                Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun V5Shell(selected: V5Page, navigate: (V5Page) -> Unit, content: @Composable () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = { V5BottomBar(selected, navigate) }
    ) { pad -> Box(Modifier.fillMaxSize().padding(pad)) { content() } }
}

@Composable
private fun V5BottomBar(selected: V5Page, navigate: (V5Page) -> Unit) {
    Surface(Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surface.copy(alpha = .98f), shadowElevation = 18.dp) {
        Row(Modifier.fillMaxWidth().navigationBarsPadding().height(76.dp).padding(horizontal = 5.dp, vertical = 7.dp)) {
            v5Navigation.forEach { item ->
                val active = item.page == selected
                Column(
                    Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(18.dp)).clickable { navigate(item.page) }.padding(vertical = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(Modifier.width(if (active) 48.dp else 36.dp).height(29.dp).clip(RoundedCornerShape(15.dp)).background(if (active) MaterialTheme.colorScheme.primaryContainer else Color.Transparent), contentAlignment = Alignment.Center) {
                        Icon(item.icon, null, tint = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(21.dp))
                    }
                    Text(item.label, color = if (active) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, fontWeight = if (active) FontWeight.ExtraBold else FontWeight.Medium)
                }
            }
        }
    }
}

@Composable
private fun V5Top(title: String, subtitle: String? = null, trailing: @Composable (() -> Unit)? = null) {
    Row(Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 20.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            subtitle?.let { Text(it, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, modifier = Modifier.padding(top = 2.dp)) }
        }
        trailing?.invoke()
    }
}

@Composable
private fun V5Home(openPlay: () -> Unit, openMemory: () -> Unit, openMosques: () -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Row(Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 20.dp, vertical = 14.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(Modifier.size(46.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) { Icon(Icons.Rounded.Person, null, tint = MaterialTheme.colorScheme.primary) }
                Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                    Text("As-salāmu ʿalaykum", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                    Text("Youssef", fontWeight = FontWeight.ExtraBold, fontSize = 19.sp)
                }
                Surface(shape = RoundedCornerShape(18.dp), color = V5Gold.copy(alpha = .18f)) {
                    Text("145 XP", color = Color(0xFF806018), fontWeight = FontWeight.ExtraBold, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
                }
            }
        }
        item { V5PrayerCard(openMosques) }
        item { V5DailyHero(openPlay) }
        item {
            Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                V5MiniStat(Icons.Rounded.Star, "7 jours", "Série", V5Coral, Modifier.weight(1f))
                V5MiniStat(Icons.Rounded.School, "820 XP", "Semaine", V5Gold, Modifier.weight(1f))
                V5MiniStat(Icons.Rounded.Verified, "68%", "Maîtrise", V5Emerald, Modifier.weight(1f))
            }
        }
        item { V5Section("Jouer et apprendre", "Voir tout", openPlay) }
        item {
            Row(Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                V5ModeCard("Mémoire", "4 formats responsifs", Icons.Rounded.GridView, listOf(V5Emerald, V5EmeraldDark), Modifier.weight(1f), openMemory)
                V5ModeCard("Quiz", "Tester ses acquis", Icons.Rounded.MenuBook, listOf(V5Blue, Color(0xFF285C80)), Modifier.weight(1f), openPlay)
            }
        }
        item {
            Row(Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                V5ModeCard("Vrai ou faux", "Répondre vite", Icons.Rounded.CheckCircle, listOf(V5Gold, Color(0xFFC99421)), Modifier.weight(1f), openPlay)
                V5ModeCard("Devinettes", "Révéler des indices", Icons.Rounded.Star, listOf(V5Purple, Color(0xFF5E4A8C)), Modifier.weight(1f), openPlay)
            }
        }
    }
}

@Composable
private fun V5PrayerCard(click: () -> Unit) {
    Surface(
        Modifier.padding(horizontal = 20.dp).fillMaxWidth().clickable(onClick = click),
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 5.dp
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(54.dp).clip(RoundedCornerShape(18.dp)).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) {
                Icon(Icons.Rounded.Mosque, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(29.dp))
            }
            Column(Modifier.weight(1f).padding(horizontal = 13.dp)) {
                Text("Mosquée et prochaine prière", fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Text("Touchez pour rechercher les mosquées proches", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun V5DailyHero(click: () -> Unit) {
    Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth().height(225.dp).shadow(16.dp, RoundedCornerShape(30.dp)), shape = RoundedCornerShape(30.dp), color = V5Teal) {
        Box(Modifier.fillMaxSize()) {
            Canvas(Modifier.matchParentSize()) { drawCircle(V5Gold.copy(alpha = .14f), size.minDimension * .64f, Offset(size.width, 0f)) }
            Column(Modifier.fillMaxSize().padding(22.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = RoundedCornerShape(12.dp), color = Color.White.copy(alpha = .10f)) { Text("PARCOURS DU JOUR", color = V5Gold, fontWeight = FontWeight.ExtraBold, fontSize = 9.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) }
                    Spacer(Modifier.weight(1f)); Icon(Icons.Rounded.Star, null, tint = V5Gold)
                }
                Text("Les valeurs\net leurs enseignements", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 25.sp, lineHeight = 28.sp, modifier = Modifier.padding(top = 17.dp))
                Text("6 minutes • 3 activités", color = Color.White.copy(alpha = .66f), fontSize = 10.sp, modifier = Modifier.padding(top = 5.dp))
                Spacer(Modifier.weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        LinearProgressIndicator(progress = { .42f }, modifier = Modifier.fillMaxWidth().height(6.dp).clip(CircleShape), color = V5Gold, trackColor = Color.White.copy(alpha = .13f))
                        Text("42% terminé", color = Color.White.copy(alpha = .62f), fontSize = 9.sp, modifier = Modifier.padding(top = 5.dp))
                    }
                    Spacer(Modifier.width(14.dp))
                    Button(click, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = V5EmeraldDark), shape = RoundedCornerShape(16.dp)) {
                        Text("Continuer", fontWeight = FontWeight.ExtraBold)
                        Icon(Icons.Rounded.ArrowForward, null, modifier = Modifier.padding(start = 5.dp).size(18.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.V5MiniStat(icon: ImageVector, value: String, label: String, color: Color, modifier: Modifier) {
    Surface(modifier, shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.surface, shadowElevation = 3.dp) {
        Column(Modifier.padding(vertical = 13.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = color, modifier = Modifier.size(21.dp))
            Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, modifier = Modifier.padding(top = 6.dp))
            Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp)
        }
    }
}

@Composable
private fun V5Section(title: String, action: String, click: () -> Unit) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp, modifier = Modifier.weight(1f))
        TextButton(onClick = click) { Text(action, color = MaterialTheme.colorScheme.primary, fontSize = 10.sp) }
    }
}

@Composable
private fun RowScope.V5ModeCard(title: String, subtitle: String, icon: ImageVector, colors: List<Color>, modifier: Modifier, click: () -> Unit) {
    Surface(modifier.height(140.dp).clip(RoundedCornerShape(25.dp)).clickable(onClick = click), color = Color.Transparent, shadowElevation = 8.dp) {
        Box(Modifier.fillMaxSize().background(Brush.linearGradient(colors)).padding(16.dp)) {
            Canvas(Modifier.matchParentSize()) { drawCircle(Color.White.copy(alpha = .08f), size.minDimension * .62f, Offset(size.width, 0f)) }
            Box(Modifier.size(40.dp).clip(RoundedCornerShape(14.dp)).background(Color.White.copy(alpha = .15f)), contentAlignment = Alignment.Center) { Icon(icon, null, tint = Color.White) }
            Column(Modifier.align(Alignment.BottomStart)) {
                Text(title, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
                Text(subtitle, color = Color.White.copy(alpha = .72f), fontSize = 9.sp)
            }
        }
    }
}

@Composable
private fun V5Play(openMemory: () -> Unit) {
    val modes = listOf(
        Triple("Mémoire islamique", "Choisissez 4 × 4, 4 × 5, 4 × 6 ou 4 × 7.", Icons.Rounded.GridView),
        Triple("Quiz", "Quatre réponses et une explication claire.", Icons.Rounded.MenuBook),
        Triple("Vrai ou faux", "Des affirmations courtes et chronométrées.", Icons.Rounded.CheckCircle),
        Triple("Devinettes", "Des indices révélés progressivement.", Icons.Rounded.Star)
    )
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(11.dp)) {
        item { V5Top("Jouer", "Choisissez une expérience") }
        item { Box(Modifier.padding(horizontal = 20.dp)) { V5InfoStrip(Icons.Rounded.Info, "Conseil du jour", "Une partie courte suffit pour maintenir votre série.") } }
        items(modes) { mode ->
            Surface(
                Modifier.padding(horizontal = 20.dp).fillMaxWidth().clickable { if (mode.first == "Mémoire islamique") openMemory() },
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 4.dp
            ) {
                Row(Modifier.padding(17.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(60.dp).clip(RoundedCornerShape(20.dp)).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) { Icon(mode.third, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(31.dp)) }
                    Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                        Text(mode.first, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
                        Text(mode.second, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp, lineHeight = 14.sp, modifier = Modifier.padding(top = 4.dp))
                        Text(if (mode.first == "Mémoire islamique") "Choisir un format →" else "Bientôt disponible", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 10.sp, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionCenter(onBack: () -> Unit, onContinue: () -> Unit) {
    val context = LocalContext.current
    var refresh by remember { mutableIntStateOf(0) }
    val notificationGranted = remember(refresh) { hasNotificationPermission(context) }
    val foregroundGranted = remember(refresh) { hasForegroundLocation(context) }
    val backgroundGranted = remember(refresh) { hasBackgroundLocation(context) }

    val notificationLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { refresh++ }
    val foregroundLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { refresh++ }
    val backgroundLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { refresh++ }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            Surface(color = MaterialTheme.colorScheme.background.copy(alpha = .98f)) {
                Column(Modifier.navigationBarsPadding().padding(horizontal = 20.dp, vertical = 12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    V5MainButton("Continuer vers les formats", Icons.Rounded.ArrowForward, onContinue)
                    Text(
                        "Le jeu reste accessible si une autorisation est refusée. Les fonctions concernées seront simplement désactivées.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 9.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) { pad ->
        LazyColumn(Modifier.fillMaxSize().padding(pad), contentPadding = PaddingValues(bottom = 18.dp), verticalArrangement = Arrangement.spacedBy(11.dp)) {
            item { V5Top("Autorisations", "Prières, défis éducatifs et mosquées proches") { IconButton(onClick = onBack) { Icon(Icons.Rounded.Close, null) } } }
            item {
                Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(28.dp), color = V5Teal, shadowElevation = 8.dp) {
                    Column(Modifier.padding(20.dp)) {
                        Icon(Icons.Rounded.Security, null, tint = V5Gold, modifier = Modifier.size(36.dp))
                        Text("Votre vie privée reste prioritaire", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 21.sp, modifier = Modifier.padding(top = 10.dp))
                        Text("Android laisse toujours la décision finale à l’utilisateur. Muslim QI explique chaque demande et n’utilise que les données nécessaires.", color = Color.White.copy(alpha = .72f), fontSize = 11.sp, lineHeight = 16.sp, modifier = Modifier.padding(top = 7.dp))
                    }
                }
            }
            item {
                PermissionCard(
                    icon = Icons.Rounded.NotificationsActive,
                    title = "Notifications",
                    body = "Rappels de prière et défi éducatif quotidien.",
                    granted = notificationGranted,
                    action = {
                        if (Build.VERSION.SDK_INT >= 33) notificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS) else refresh++
                    }
                )
            }
            item {
                PermissionCard(
                    icon = Icons.Rounded.LocationOn,
                    title = "Position pendant l’utilisation",
                    body = "Recherche des mosquées réellement proches et calcul local des horaires.",
                    granted = foregroundGranted,
                    action = {
                        foregroundLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
                    }
                )
            }
            item {
                PermissionCard(
                    icon = Icons.Rounded.Map,
                    title = "Position en arrière-plan",
                    body = "Option réservée aux futurs rappels géolocalisés. Elle est demandée séparément après la position normale.",
                    granted = backgroundGranted,
                    enabled = foregroundGranted,
                    action = {
                        if (Build.VERSION.SDK_INT >= 29) backgroundLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION) else refresh++
                    }
                )
            }
            item {
                Box(Modifier.padding(horizontal = 20.dp)) {
                    V5InfoStrip(Icons.Rounded.Info, "Important", "Muslim QI ne bloque pas l’apprentissage si Android refuse une autorisation. La localisation en arrière-plan n’est pas utilisée par le moteur de jeu.")
                }
            }
        }
    }
}

@Composable
private fun PermissionCard(icon: ImageVector, title: String, body: String, granted: Boolean, enabled: Boolean = true, action: () -> Unit) {
    Surface(
        Modifier.padding(horizontal = 20.dp).fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 3.dp
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(50.dp).clip(RoundedCornerShape(17.dp)).background(if (granted) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant), contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = if (granted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(Modifier.weight(1f).padding(horizontal = 13.dp)) {
                Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp)
                Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, lineHeight = 13.sp)
            }
            if (granted) {
                Icon(Icons.Rounded.CheckCircle, null, tint = MaterialTheme.colorScheme.primary)
            } else {
                OutlinedButton(onClick = action, enabled = enabled, contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)) { Text(if (enabled) "Autoriser" else "Après", fontSize = 9.sp) }
            }
        }
    }
}

@Composable
private fun MemorySetupPage(selectedFormatIndex: Int, onSelected: (Int) -> Unit, onBack: () -> Unit, onStart: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        bottomBar = {
            Surface(color = MaterialTheme.colorScheme.background.copy(alpha = .98f)) {
                Box(Modifier.navigationBarsPadding().padding(horizontal = 20.dp, vertical = 12.dp)) { V5MainButton("Lancer la partie", Icons.Rounded.PlayArrow, onStart) }
            }
        }
    ) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).statusBarsPadding().padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.Rounded.ArrowBack, null) }
                Column(Modifier.weight(1f)) {
                    Text("Mémoire islamique", style = MaterialTheme.typography.titleLarge)
                    Text("Choisissez un format", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                }
                Surface(shape = RoundedCornerShape(14.dp), color = MaterialTheme.colorScheme.primaryContainer) { Text("${gridFormats[selectedFormatIndex].pairCount} paires", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) }
            }
            Surface(Modifier.fillMaxWidth(), shape = RoundedCornerShape(25.dp), color = V5Teal, shadowElevation = 8.dp) {
                Row(Modifier.padding(17.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(55.dp).clip(RoundedCornerShape(18.dp)).background(Color.White.copy(alpha = .10f)), contentAlignment = Alignment.Center) { Icon(Icons.Rounded.GridView, null, tint = V5Gold, modifier = Modifier.size(30.dp)) }
                    Column(Modifier.padding(start = 14.dp)) {
                        Text("Grille responsive sans défilement", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                        Text("Les cartes s’adaptent automatiquement à la hauteur et à la largeur du téléphone.", color = Color.White.copy(alpha = .68f), fontSize = 9.sp, lineHeight = 13.sp)
                    }
                }
            }
            Text("Formats disponibles", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                gridFormats.chunked(2).forEachIndexed { rowIndex, rowFormats ->
                    Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        rowFormats.forEachIndexed { colIndex, format ->
                            val index = rowIndex * 2 + colIndex
                            FormatCard(format, selectedFormatIndex == index, Modifier.weight(1f).fillMaxHeight()) { onSelected(index) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FormatCard(format: GridFormat, selected: Boolean, modifier: Modifier, click: () -> Unit) {
    Surface(
        modifier = modifier.clickable(onClick = click).border(1.5.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        color = if (selected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
        shadowElevation = if (selected) 8.dp else 3.dp
    ) {
        Box(Modifier.fillMaxSize().padding(14.dp)) {
            if (selected) Icon(Icons.Rounded.CheckCircle, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.align(Alignment.TopEnd))
            Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                MiniGridPreview(format.columns, format.rows, selected)
                Text(format.label, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, modifier = Modifier.padding(top = 10.dp))
                Text(format.level, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                Text("${format.cardCount} cartes", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 9.sp, modifier = Modifier.padding(top = 5.dp))
            }
        }
    }
}

@Composable
private fun MiniGridPreview(columns: Int, rows: Int, selected: Boolean) {
    Column(Modifier.height(62.dp).width(70.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
        repeat(rows) {
            Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                repeat(columns) {
                    Box(Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(2.dp)).background(if (selected) MaterialTheme.colorScheme.primary.copy(alpha = .75f) else MaterialTheme.colorScheme.outline.copy(alpha = .50f)))
                }
            }
        }
    }
}

@Composable
private fun MemoryGamePage(format: GridFormat, onBack: () -> Unit) {
    var restartKey by rememberSaveable { mutableIntStateOf(0) }
    val deck = remember(format, restartKey) { buildDeck(format) }
    val selected = remember(restartKey) { mutableStateListOf<Int>() }
    val matchedPairs = remember(restartKey) { mutableStateListOf<Int>() }
    var attempts by rememberSaveable(restartKey) { mutableIntStateOf(0) }
    var errors by rememberSaveable(restartKey) { mutableIntStateOf(0) }
    var score by rememberSaveable(restartKey) { mutableIntStateOf(0) }
    var elapsed by rememberSaveable(restartKey) { mutableIntStateOf(0) }
    var paused by rememberSaveable(restartKey) { mutableStateOf(false) }
    val completed = matchedPairs.size == format.pairCount

    LaunchedEffect(restartKey, paused, completed) {
        while (!paused && !completed) {
            delay(1000)
            elapsed++
        }
    }

    LaunchedEffect(selected.toList()) {
        if (selected.size == 2) {
            attempts++
            delay(520)
            val first = deck[selected[0]]
            val second = deck[selected[1]]
            if (first.pairId == second.pairId) {
                if (first.pairId !in matchedPairs) matchedPairs.add(first.pairId)
                score += 100 + (format.rows * 5)
            } else {
                errors++
                score = (score - 3).coerceAtLeast(0)
            }
            selected.clear()
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { pad ->
        Column(Modifier.fillMaxSize().padding(pad).statusBarsPadding().navigationBarsPadding().padding(horizontal = 10.dp, vertical = 7.dp)) {
            Row(Modifier.fillMaxWidth().height(44.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.Rounded.ArrowBack, null) }
                Column(Modifier.weight(1f)) {
                    Text("Mémoire islamique", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
                    Text("${format.label} • ${format.pairCount} paires", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp)
                }
                Surface(shape = RoundedCornerShape(14.dp), color = V5Gold.copy(alpha = .18f)) { Text("$score pts", color = Color(0xFF826116), fontWeight = FontWeight.ExtraBold, fontSize = 10.sp, modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp)) }
                IconButton(onClick = { paused = !paused }) { Icon(if (paused) Icons.Rounded.PlayArrow else Icons.Rounded.Pause, null) }
            }
            Row(Modifier.fillMaxWidth().height(42.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                GameStat(formatTime(elapsed), "Temps")
                GameStat(attempts.toString(), "Essais")
                GameStat(errors.toString(), "Erreurs")
                GameStat("${matchedPairs.size}/${format.pairCount}", "Paires")
            }
            LinearProgressIndicator(
                progress = { matchedPairs.size / format.pairCount.toFloat() },
                modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(Modifier.height(6.dp))
            BoxWithConstraints(Modifier.fillMaxWidth().weight(1f)) {
                val gap = when {
                    maxHeight < 450.dp -> 3.dp
                    format.rows >= 7 -> 4.dp
                    else -> 6.dp
                }
                Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(gap)) {
                    deck.chunked(format.columns).forEach { rowCards ->
                        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(gap)) {
                            rowCards.forEach { card ->
                                val index = deck.indexOf(card)
                                val shown = index in selected || card.pairId in matchedPairs
                                val matched = card.pairId in matchedPairs
                                ResponsiveMemoryCard(
                                    model = card,
                                    shown = shown,
                                    matched = matched,
                                    compact = format.rows >= 6,
                                    modifier = Modifier.weight(1f).fillMaxHeight(),
                                    click = {
                                        if (!paused && !completed && selected.size < 2 && index !in selected && !matched) selected.add(index)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (paused) {
        AlertDialog(
            onDismissRequest = { paused = false },
            icon = { Icon(Icons.Rounded.Pause, null, tint = MaterialTheme.colorScheme.primary) },
            title = { Text("Partie en pause") },
            text = { Text("Le chronomètre est arrêté. Reprenez quand vous êtes prêt.") },
            confirmButton = { Button(onClick = { paused = false }) { Text("Reprendre") } },
            dismissButton = { TextButton(onClick = onBack) { Text("Quitter") } },
            shape = RoundedCornerShape(28.dp)
        )
    }

    if (completed) {
        AlertDialog(
            onDismissRequest = {},
            icon = { Icon(Icons.Rounded.EmojiEvents, null, tint = V5Gold, modifier = Modifier.size(44.dp)) },
            title = { Text("Grille terminée !", fontWeight = FontWeight.ExtraBold) },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("$score points", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
                    Text("${format.label} • $attempts tentatives • $errors erreurs • ${formatTime(elapsed)}", color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center, fontSize = 11.sp, modifier = Modifier.padding(top = 7.dp))
                }
            },
            confirmButton = { Button(onClick = { restartKey++ }) { Text("Rejouer") } },
            dismissButton = { TextButton(onClick = onBack) { Text("Formats") } },
            shape = RoundedCornerShape(28.dp)
        )
    }
}

@Composable
private fun GameStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 8.sp)
    }
}

@Composable
private fun ResponsiveMemoryCard(model: MemoryCardModel, shown: Boolean, matched: Boolean, compact: Boolean, modifier: Modifier, click: () -> Unit) {
    val rotation by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (shown) 180f else 0f,
        animationSpec = tween(320),
        label = "card_flip"
    )
    val displayFront = rotation > 90f
    Surface(
        modifier = modifier.graphicsLayer { rotationY = rotation; cameraDistance = 12f * density }.clickable(onClick = click),
        shape = RoundedCornerShape(if (compact) 11.dp else 15.dp),
        color = if (displayFront) MaterialTheme.colorScheme.surface else V5EmeraldDark,
        shadowElevation = if (matched) 1.dp else 4.dp
    ) {
        Box(
            Modifier.fillMaxSize().graphicsLayer { if (displayFront) rotationY = 180f }.border(
                width = if (matched) 2.dp else 1.dp,
                color = if (matched) V5Gold else V5Gold.copy(alpha = .48f),
                shape = RoundedCornerShape(if (compact) 11.dp else 15.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            if (!displayFront) {
                Canvas(Modifier.fillMaxSize()) {
                    val center = Offset(size.width / 2, size.height / 2)
                    drawCircle(V5Gold.copy(alpha = .18f), size.minDimension * .28f, center)
                    drawCircle(V5Gold.copy(alpha = .58f), size.minDimension * .19f, center, style = Stroke(2.dp.toPx()))
                    drawLine(V5Gold.copy(alpha = .70f), center + Offset(0f, -size.minDimension * .14f), center + Offset(0f, size.minDimension * .14f), 2.dp.toPx())
                    drawLine(V5Gold.copy(alpha = .70f), center + Offset(-size.minDimension * .14f, 0f), center + Offset(size.minDimension * .14f, 0f), 2.dp.toPx())
                }
                Text("✦", color = V5Gold, fontSize = if (compact) 14.sp else 20.sp)
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(2.dp)) {
                    Text(model.definition.arabic, color = if (matched) V5Emerald else MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.ExtraBold, fontSize = if (compact) 11.sp else 15.sp, maxLines = 1)
                    Text(model.definition.french, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold, fontSize = if (compact) 6.sp else 8.sp, maxLines = 1, textAlign = TextAlign.Center)
                    if (matched) Icon(Icons.Rounded.Check, null, tint = V5Gold, modifier = Modifier.size(if (compact) 10.dp else 14.dp))
                }
            }
        }
    }
}

@Composable
private fun NearbyMosquesPage(onBack: () -> Unit) {
    val context = LocalContext.current
    var refresh by remember { mutableIntStateOf(0) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var mosques by remember { mutableStateOf<List<MosqueResult>>(emptyList()) }
    var selected by remember { mutableStateOf<MosqueResult?>(null) }
    var prayerTimes by remember { mutableStateOf<PrayerTimesResult?>(null) }
    val foregroundGranted = remember(refresh) { hasForegroundLocation(context) }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { refresh++ }

    LaunchedEffect(foregroundGranted, refresh) {
        if (foregroundGranted) {
            loading = true
            error = null
            try {
                val location = getCurrentBestLocation(context)
                if (location == null) {
                    error = "Position indisponible. Activez le GPS puis réessayez."
                } else {
                    mosques = fetchNearbyMosques(location.latitude, location.longitude)
                    if (mosques.isEmpty()) error = "Aucune mosquée renseignée dans un rayon de 12 km."
                }
            } catch (e: Exception) {
                error = "La recherche n’a pas pu être terminée : ${e.message ?: "erreur réseau"}."
            } finally {
                loading = false
            }
        }
    }

    LaunchedEffect(selected) {
        prayerTimes = null
        selected?.let { mosque ->
            prayerTimes = try { fetchCalculatedPrayerTimes(mosque.latitude, mosque.longitude) } catch (_: Exception) { null }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.Transparent) { pad ->
        LazyColumn(Modifier.fillMaxSize().padding(pad), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(11.dp)) {
            item {
                Row(Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 12.dp, vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) { Icon(Icons.Rounded.ArrowBack, null) }
                    Column(Modifier.weight(1f)) {
                        Text("Mosquées proches", style = MaterialTheme.typography.titleLarge)
                        Text("Recherche réelle à partir de votre position", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                    }
                    IconButton(onClick = { refresh++ }) { Icon(Icons.Rounded.Refresh, null) }
                }
            }
            if (!foregroundGranted) {
                item {
                    Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(28.dp), color = V5Teal, shadowElevation = 8.dp) {
                        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Rounded.LocationOn, null, tint = V5Gold, modifier = Modifier.size(44.dp))
                            Text("Autorisez la position", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 21.sp, modifier = Modifier.padding(top = 10.dp))
                            Text("La position est utilisée uniquement pour classer les mosquées par distance et calculer les horaires locaux.", color = Color.White.copy(alpha = .72f), textAlign = TextAlign.Center, fontSize = 11.sp, modifier = Modifier.padding(top = 7.dp))
                            Button(onClick = { permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)) }, modifier = Modifier.padding(top = 16.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = V5EmeraldDark)) { Text("Autoriser la position") }
                        }
                    }
                }
            } else {
                item {
                    Box(Modifier.padding(horizontal = 20.dp)) {
                        V5InfoStrip(Icons.Rounded.Info, "Données des mosquées", "Les établissements proches proviennent d’OpenStreetMap. Les horaires affichés sont calculés à leurs coordonnées, sauf lorsqu’un fournisseur officiel de la mosquée est connecté.")
                    }
                }
                if (loading) {
                    item { Box(Modifier.fillMaxWidth().height(160.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }
                }
                error?.let { message ->
                    item {
                        Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = V5Coral.copy(alpha = .12f)) {
                            Text(message, modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.onSurface, fontSize = 12.sp)
                        }
                    }
                }
                items(mosques) { mosque ->
                    MosqueCard(mosque, selected?.name == mosque.name) { selected = mosque }
                }
            }
        }
    }

    selected?.let { mosque ->
        AlertDialog(
            onDismissRequest = { selected = null },
            icon = { Icon(Icons.Rounded.Mosque, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(42.dp)) },
            title = { Text(mosque.name, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center) },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text("À environ ${formatDistance(mosque.distanceMeters)}", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
                    if (prayerTimes == null) {
                        CircularProgressIndicator(modifier = Modifier.padding(18.dp).size(28.dp), strokeWidth = 3.dp)
                    } else {
                        PrayerTimeGrid(prayerTimes!!)
                        Text(prayerTimes!!.sourceLabel, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 10.dp))
                    }
                }
            },
            confirmButton = {
                Button(onClick = { selected = null }) { Text("Fermer") }
            },
            dismissButton = {
                mosque.website?.let { site ->
                    TextButton(onClick = { openWebsite(context, site) }) { Text("Site de la mosquée") }
                }
            },
            shape = RoundedCornerShape(28.dp)
        )
    }
}

@Composable
private fun MosqueCard(mosque: MosqueResult, selected: Boolean, click: () -> Unit) {
    Surface(
        Modifier.padding(horizontal = 20.dp).fillMaxWidth().clickable(onClick = click).border(1.dp, if (selected) MaterialTheme.colorScheme.primary else Color.Transparent, RoundedCornerShape(22.dp)),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 3.dp
    ) {
        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(52.dp).clip(RoundedCornerShape(18.dp)).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) { Icon(Icons.Rounded.Mosque, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(28.dp)) }
            Column(Modifier.weight(1f).padding(horizontal = 13.dp)) {
                Text(mosque.name, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, maxLines = 2)
                Text("${formatDistance(mosque.distanceMeters)} • toucher pour les horaires", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp)
            }
            Icon(Icons.Rounded.ChevronRight, null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}

@Composable
private fun PrayerTimeGrid(times: PrayerTimesResult) {
    Column(Modifier.fillMaxWidth().padding(top = 14.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
        listOf(
            "Fajr" to times.fajr,
            "Dhuhr" to times.dhuhr,
            "Asr" to times.asr,
            "Maghrib" to times.maghrib,
            "Isha" to times.isha
        ).chunked(2).forEach { row ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                row.forEach { item ->
                    Surface(Modifier.weight(1f), shape = RoundedCornerShape(14.dp), color = MaterialTheme.colorScheme.primaryContainer) {
                        Column(Modifier.padding(9.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(item.first, color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 9.sp)
                            Text(item.second, color = MaterialTheme.colorScheme.onPrimaryContainer, fontWeight = FontWeight.ExtraBold, fontSize = 15.sp)
                        }
                    }
                }
                if (row.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun V5Progress() {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(13.dp)) {
        item { V5Top("Progression", "Votre chemin de connaissance") }
        item {
            Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(28.dp), color = V5Teal, shadowElevation = 8.dp) {
                Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("NIVEAU 12", color = V5Gold, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
                        Text("Chercheur de science", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, modifier = Modifier.padding(top = 4.dp))
                        Text("380 XP avant le niveau suivant", color = Color.White.copy(alpha = .64f), fontSize = 9.sp)
                        LinearProgressIndicator(progress = { .68f }, modifier = Modifier.padding(top = 12.dp).fillMaxWidth().height(7.dp).clip(CircleShape), color = V5Gold, trackColor = Color.White.copy(alpha = .13f))
                    }
                    Box(Modifier.padding(start = 14.dp).size(72.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(progress = { .68f }, modifier = Modifier.fillMaxSize(), color = V5Gold, trackColor = Color.White.copy(alpha = .12f), strokeWidth = 7.dp)
                        Text("68%", color = Color.White, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
        item { V5Section("Maîtrise par thème", "", {}) }
        item {
            Row(Modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(9.dp)) {
                MasteryMini("Coran", .80f, V5Emerald, Modifier.weight(1f))
                MasteryMini("Histoire", .72f, V5Blue, Modifier.weight(1f))
                MasteryMini("Valeurs", .65f, V5Gold, Modifier.weight(1f))
            }
        }
        item { V5Section("À revoir", "3 thèmes", {}) }
        items(listOf("Les piliers de la foi" to .42f, "Les ablutions" to .58f, "Les mois hégiriens" to .64f)) { review ->
            Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = MaterialTheme.colorScheme.surface, shadowElevation = 3.dp) {
                Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Rounded.MenuBook, null, tint = MaterialTheme.colorScheme.primary)
                    Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                        Text(review.first, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        LinearProgressIndicator(progress = { review.second }, modifier = Modifier.fillMaxWidth().height(5.dp).clip(CircleShape), color = V5Gold, trackColor = V5Sand)
                    }
                    Text("${(review.second * 100).toInt()}%", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
private fun RowScope.MasteryMini(label: String, progress: Float, color: Color, modifier: Modifier) {
    Surface(modifier, shape = RoundedCornerShape(22.dp), color = MaterialTheme.colorScheme.surface, shadowElevation = 3.dp) {
        Column(Modifier.padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.size(58.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxSize(), color = color, trackColor = color.copy(alpha = .12f), strokeWidth = 6.dp)
                Text("${(progress * 100).toInt()}%", fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
            }
            Text(label, fontWeight = FontWeight.Bold, fontSize = 9.sp, modifier = Modifier.padding(top = 7.dp))
        }
    }
}

@Composable
private fun V5Ranking() {
    val list = listOf("Aïcha" to 1640, "Youssef" to 1520, "Amine" to 1410, "Fatima" to 1280, "Hassan" to 1190)
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item { V5Top("Classement", "Une motivation facultative") }
        item {
            Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(28.dp), color = V5Teal, shadowElevation = 8.dp) {
                Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Rounded.EmojiEvents, null, tint = V5Gold, modifier = Modifier.size(42.dp))
                    Text("Vous êtes 2e cette semaine", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 19.sp, modifier = Modifier.padding(top = 9.dp))
                    Text("Encore 120 XP pour atteindre la première place", color = Color.White.copy(alpha = .64f), fontSize = 9.sp)
                    LinearProgressIndicator(progress = { .82f }, modifier = Modifier.padding(top = 13.dp).fillMaxWidth().height(7.dp).clip(CircleShape), color = V5Gold, trackColor = Color.White.copy(alpha = .13f))
                }
            }
        }
        item { V5Section("Classement hebdomadaire", "Top 100", {}) }
        items(list) { entry ->
            val rank = list.indexOf(entry) + 1
            val me = entry.first == "Youssef"
            Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(20.dp), color = if (me) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface, shadowElevation = if (me) 6.dp else 3.dp) {
                Row(Modifier.padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(36.dp).clip(CircleShape).background(V5Gold.copy(alpha = .22f)), contentAlignment = Alignment.Center) { Text(rank.toString(), fontWeight = FontWeight.ExtraBold) }
                    Icon(Icons.Rounded.Person, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 10.dp))
                    Text(if (me) "${entry.first} • Vous" else entry.first, Modifier.weight(1f).padding(horizontal = 12.dp), fontWeight = FontWeight.Bold)
                    Text("${entry.second} XP", color = if (me) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
private fun V5Profile(dark: Boolean, setDark: (Boolean) -> Unit, rtl: Boolean, setRtl: (Boolean) -> Unit, openPermissions: () -> Unit, openMosques: () -> Unit) {
    var sounds by rememberSaveable { mutableStateOf(true) }
    var vibration by rememberSaveable { mutableStateOf(true) }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 28.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item { V5Top("Profil", "Paramètres et confidentialité") { Icon(Icons.Rounded.Settings, null, tint = MaterialTheme.colorScheme.primary) } }
        item {
            Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(28.dp), color = V5Teal, shadowElevation = 8.dp) {
                Row(Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(64.dp).clip(CircleShape).background(V5Emerald), contentAlignment = Alignment.Center) { Icon(Icons.Rounded.Person, null, tint = Color.White, modifier = Modifier.size(34.dp)) }
                    Column(Modifier.weight(1f).padding(horizontal = 14.dp)) {
                        Text("Youssef", color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        Text("Mode invité", color = Color.White.copy(alpha = .64f), fontSize = 10.sp)
                        Text("Niveau 12", color = V5Gold, fontWeight = FontWeight.Bold, fontSize = 10.sp, modifier = Modifier.padding(top = 5.dp))
                    }
                    V5Logo(50.dp)
                }
            }
        }
        item { ProfileHeading("Préférences") }
        item {
            SettingsSurface {
                V5SwitchRow(Icons.Rounded.DarkMode, "Mode sombre", "Réduire la luminosité", dark, setDark)
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                V5SwitchRow(Icons.Rounded.Translate, "Interface arabe RTL", "Inverser la mise en page", rtl, setRtl)
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                V5SwitchRow(Icons.Rounded.NotificationsActive, "Effets sonores", "Pendant les jeux", sounds) { sounds = it }
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                V5SwitchRow(Icons.Rounded.Settings, "Vibration", "Retour tactile léger", vibration) { vibration = it }
            }
        }
        item { ProfileHeading("Prières et localisation") }
        item {
            SettingsSurface {
                V5ActionRow(Icons.Rounded.Security, "Gérer les autorisations", "Notifications et localisation", openPermissions)
                HorizontalDivider(color = MaterialTheme.colorScheme.outline)
                V5ActionRow(Icons.Rounded.Mosque, "Mosquées proches", "Recherche réelle autour de vous", openMosques)
            }
        }
        item { Text("Muslim QI • Version 0.5.0", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) }
    }
}

@Composable
private fun ProfileHeading(text: String) { Text(text, fontWeight = FontWeight.ExtraBold, fontSize = 14.sp, modifier = Modifier.padding(horizontal = 20.dp)) }

@Composable
private fun SettingsSurface(content: @Composable ColumnScope.() -> Unit) {
    Surface(Modifier.padding(horizontal = 20.dp).fillMaxWidth(), shape = RoundedCornerShape(24.dp), color = MaterialTheme.colorScheme.surface, shadowElevation = 4.dp) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 4.dp), content = content)
    }
}

@Composable
private fun V5SwitchRow(icon: ImageVector, title: String, subtitle: String, checked: Boolean, change: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(40.dp).clip(RoundedCornerShape(13.dp)).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) { Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(21.dp)) }
        Column(Modifier.weight(1f).padding(horizontal = 11.dp)) {
            Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp)
        }
        Switch(checked, change)
    }
}

@Composable
private fun V5ActionRow(icon: ImageVector, title: String, subtitle: String, click: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = click).padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(40.dp).clip(RoundedCornerShape(13.dp)).background(MaterialTheme.colorScheme.primaryContainer), contentAlignment = Alignment.Center) { Icon(icon, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(21.dp)) }
        Column(Modifier.weight(1f).padding(horizontal = 11.dp)) {
            Text(title, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
            Text(subtitle, color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 9.sp)
        }
        Icon(Icons.Rounded.ChevronRight, null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

private fun buildDeck(format: GridFormat): List<MemoryCardModel> {
    val selectedPairs = pairLibrary.take(format.pairCount)
    return selectedPairs.flatMapIndexed { pairIndex, definition ->
        listOf(
            MemoryCardModel(pairIndex * 2, pairIndex, definition),
            MemoryCardModel(pairIndex * 2 + 1, pairIndex, definition)
        )
    }.shuffled(Random(System.nanoTime()))
}

private fun formatTime(seconds: Int): String = String.format(Locale.US, "%02d:%02d", seconds / 60, seconds % 60)

private fun hasPermission(context: Context, permission: String): Boolean =
    Build.VERSION.SDK_INT < 23 || context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

private fun hasNotificationPermission(context: Context): Boolean =
    Build.VERSION.SDK_INT < 33 || hasPermission(context, Manifest.permission.POST_NOTIFICATIONS)

private fun hasForegroundLocation(context: Context): Boolean =
    hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) || hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)

private fun hasBackgroundLocation(context: Context): Boolean =
    Build.VERSION.SDK_INT < 29 || hasPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION)

@SuppressLint("MissingPermission")
private suspend fun getCurrentBestLocation(context: Context): Location? = suspendCancellableCoroutine { continuation ->
    val manager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val providers = listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)
        .filter { runCatching { manager.isProviderEnabled(it) }.getOrDefault(false) }

    val cached = providers.mapNotNull { provider -> runCatching { manager.getLastKnownLocation(provider) }.getOrNull() }
        .maxByOrNull { it.time }
    if (cached != null && System.currentTimeMillis() - cached.time < 15 * 60 * 1000L) {
        continuation.resume(cached)
        return@suspendCancellableCoroutine
    }

    val provider = providers.firstOrNull()
    if (provider == null) {
        continuation.resume(cached)
        return@suspendCancellableCoroutine
    }

    if (Build.VERSION.SDK_INT >= 30) {
        manager.getCurrentLocation(provider, null, context.mainExecutor) { location ->
            if (continuation.isActive) continuation.resume(location ?: cached)
        }
    } else {
        val listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                manager.removeUpdates(this)
                if (continuation.isActive) continuation.resume(location)
            }
            @Deprecated("Deprecated in Java") override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit
            override fun onProviderEnabled(provider: String) = Unit
            override fun onProviderDisabled(provider: String) = Unit
        }
        manager.requestSingleUpdate(provider, listener, Looper.getMainLooper())
        continuation.invokeOnCancellation { manager.removeUpdates(listener) }
    }
}

private suspend fun fetchNearbyMosques(latitude: Double, longitude: Double): List<MosqueResult> = withContext(Dispatchers.IO) {
    val query = """
        [out:json][timeout:20];
        (
          node[\"amenity\"=\"place_of_worship\"][\"religion\"=\"muslim\"](around:12000,$latitude,$longitude);
          way[\"amenity\"=\"place_of_worship\"][\"religion\"=\"muslim\"](around:12000,$latitude,$longitude);
          relation[\"amenity\"=\"place_of_worship\"][\"religion\"=\"muslim\"](around:12000,$latitude,$longitude);
        );
        out center 30;
    """.trimIndent()
    val encoded = URLEncoder.encode(query, "UTF-8")
    val endpoints = listOf(
        "https://overpass-api.de/api/interpreter?data=$encoded",
        "https://overpass.kumi.systems/api/interpreter?data=$encoded"
    )
    var lastError: Exception? = null
    for (endpoint in endpoints) {
        try {
            val root = JSONObject(httpGet(endpoint))
            val elements = root.getJSONArray("elements")
            val results = mutableListOf<MosqueResult>()
            for (index in 0 until elements.length()) {
                val element = elements.getJSONObject(index)
                val tags = element.optJSONObject("tags") ?: JSONObject()
                val lat = if (element.has("lat")) element.optDouble("lat") else element.optJSONObject("center")?.optDouble("lat") ?: Double.NaN
                val lon = if (element.has("lon")) element.optDouble("lon") else element.optJSONObject("center")?.optDouble("lon") ?: Double.NaN
                if (lat.isNaN() || lon.isNaN()) continue
                val name = tags.optString("name").ifBlank { tags.optString("name:fr") }.ifBlank { tags.optString("name:ar") }.ifBlank { "Mosquée" }
                val website = tags.optString("website").ifBlank { tags.optString("contact:website") }.ifBlank { null }
                val distance = FloatArray(1)
                Location.distanceBetween(latitude, longitude, lat, lon, distance)
                results += MosqueResult(name, lat, lon, distance[0].roundToInt(), website)
            }
            return@withContext results.distinctBy { "${it.name}-${it.latitude}-${it.longitude}" }.sortedBy { it.distanceMeters }.take(12)
        } catch (e: Exception) {
            lastError = e
        }
    }
    throw lastError ?: IllegalStateException("Service cartographique indisponible")
}

private suspend fun fetchCalculatedPrayerTimes(latitude: Double, longitude: Double): PrayerTimesResult = withContext(Dispatchers.IO) {
    val timestamp = System.currentTimeMillis() / 1000L
    val url = "https://api.aladhan.com/v1/timings/$timestamp?latitude=$latitude&longitude=$longitude&method=3"
    val root = JSONObject(httpGet(url))
    val timings = root.getJSONObject("data").getJSONObject("timings")
    PrayerTimesResult(
        fajr = cleanTime(timings.getString("Fajr")),
        dhuhr = cleanTime(timings.getString("Dhuhr")),
        asr = cleanTime(timings.getString("Asr")),
        maghrib = cleanTime(timings.getString("Maghrib")),
        isha = cleanTime(timings.getString("Isha")),
        sourceLabel = "Horaires calculés aux coordonnées de la mosquée • méthode Ligue islamique mondiale. Les horaires officiels/iqama exigent une source publiée par la mosquée."
    )
}

private fun cleanTime(value: String): String = value.substringBefore(" ").trim()

private fun httpGet(url: String): String {
    val connection = URL(url).openConnection() as HttpURLConnection
    return try {
        connection.requestMethod = "GET"
        connection.connectTimeout = 12_000
        connection.readTimeout = 20_000
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "MuslimQI/0.5 Android")
        val code = connection.responseCode
        if (code !in 200..299) throw IllegalStateException("HTTP $code")
        connection.inputStream.bufferedReader().use { it.readText() }
    } finally {
        connection.disconnect()
    }
}

private fun formatDistance(meters: Int): String = if (meters < 1000) "$meters m" else String.format(Locale.FRANCE, "%.1f km", meters / 1000.0)

private fun openWebsite(context: Context, website: String) {
    val normalized = if (website.startsWith("http://") || website.startsWith("https://")) website else "https://$website"
    runCatching { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(normalized))) }
}
