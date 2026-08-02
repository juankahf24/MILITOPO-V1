package com.muslimqi.design;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public final class MuslimQiView extends View {
    private static final int CREAM = Color.rgb(247, 243, 232);
    private static final int WHITE = Color.rgb(255, 253, 248);
    private static final int EMERALD = Color.rgb(14, 143, 110);
    private static final int DEEP_GREEN = Color.rgb(6, 95, 87);
    private static final int TEAL = Color.rgb(12, 61, 74);
    private static final int SAND = Color.rgb(233, 216, 182);
    private static final int GOLD = Color.rgb(212, 175, 55);
    private static final int PURPLE = Color.rgb(91, 72, 142);
    private static final int RED = Color.rgb(191, 76, 60);
    private static final int TEXT = Color.rgb(17, 54, 57);
    private static final int MUTED = Color.rgb(104, 116, 113);
    private static final int PALE_GREEN = Color.rgb(229, 243, 236);

    private static final int SPLASH = 0;
    private static final int LANGUAGE = 1;
    private static final int WELCOME = 2;
    private static final int ACCOUNT = 3;
    private static final int HOME = 4;
    private static final int PLAY = 5;
    private static final int MEMORY_SETUP = 6;
    private static final int MEMORY_GAME = 7;
    private static final int MEMORY_POPUP = 8;
    private static final int MEMORY_RESULT = 9;
    private static final int QUIZ = 10;
    private static final int TRUE_FALSE = 11;
    private static final int RIDDLE = 12;
    private static final int DAILY = 13;
    private static final int PROGRESS = 14;
    private static final int BADGES = 15;
    private static final int LEADERBOARD = 16;
    private static final int PROFILE = 17;
    private static final int PRAYER = 18;

    private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Typeface serif = Typeface.create("serif", Typeface.BOLD);
    private final Typeface sans = Typeface.create("sans", Typeface.NORMAL);
    private final Typeface sansMedium = Typeface.create("sans", Typeface.BOLD);
    private final List<Hotspot> spots = new ArrayList<>();
    private int screen = SPLASH;
    private boolean dark = false;
    private boolean arabicPreview = false;
    private float W, H, sx, sy;
    private final Handler handler = new Handler();

    private static final class Hotspot {
        final RectF r;
        final int target;
        Hotspot(RectF r, int target) { this.r = r; this.target = target; }
    }

    public MuslimQiView(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        p.setTypeface(sans);
        handler.postDelayed(() -> {
            if (screen == SPLASH) {
                screen = LANGUAGE;
                invalidate();
            }
        }, 1600);
    }

    @Override protected void onDraw(Canvas c) {
        super.onDraw(c);
        W = getWidth(); H = getHeight();
        sx = W / 390f; sy = H / 844f;
        c.save();
        c.scale(sx, sy);
        spots.clear();
        drawBackground(c);
        switch (screen) {
            case SPLASH: drawSplash(c); break;
            case LANGUAGE: drawLanguage(c); break;
            case WELCOME: drawWelcome(c); break;
            case ACCOUNT: drawAccount(c); break;
            case HOME: drawHome(c); break;
            case PLAY: drawPlay(c); break;
            case MEMORY_SETUP: drawMemorySetup(c); break;
            case MEMORY_GAME: drawMemoryGame(c, false); break;
            case MEMORY_POPUP: drawMemoryGame(c, true); break;
            case MEMORY_RESULT: drawMemoryResult(c); break;
            case QUIZ: drawQuiz(c); break;
            case TRUE_FALSE: drawTrueFalse(c); break;
            case RIDDLE: drawRiddle(c); break;
            case DAILY: drawDaily(c); break;
            case PROGRESS: drawProgress(c); break;
            case BADGES: drawBadges(c); break;
            case LEADERBOARD: drawLeaderboard(c); break;
            case PROFILE: drawProfile(c); break;
            case PRAYER: drawPrayer(c); break;
        }
        c.restore();
    }

    private void drawBackground(Canvas c) {
        c.drawColor(dark ? Color.rgb(12, 29, 31) : CREAM);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1f);
        p.setColor(dark ? Color.argb(22, 212, 175, 55) : Color.argb(24, 212, 175, 55));
        for (int k=0;k<3;k++) {
            float cx = k == 0 ? 0 : (k == 1 ? 390 : 195);
            float cy = k == 2 ? 844 : 0;
            for (int i=1;i<=5;i++) {
                float r=18*i;
                c.drawCircle(cx,cy,r,p);
                c.drawLine(cx-r,cy,cx+r,cy,p);
                c.drawLine(cx,cy-r,cx,cy+r,p);
            }
        }
        p.setStyle(Paint.Style.FILL);
    }

    private int bgCard(){ return dark ? Color.rgb(25,46,47) : WHITE; }
    private int text(){ return dark ? Color.rgb(242,239,228) : TEXT; }
    private int muted(){ return dark ? Color.rgb(166,179,173) : MUTED; }

    private void header(Canvas c, String title, String subtitle, boolean back) {
        if (back) {
            circle(c, 25, 41, 17, bgCard());
            text(c, "‹", 25, 47, 28, text(), Paint.Align.CENTER, sans);
            spot(8,24,42,58, HOME);
        }
        text(c, title, back ? 55 : 22, 44, 22, text(), Paint.Align.LEFT, sansMedium);
        if (subtitle != null) text(c, subtitle, back ? 55 : 22, 65, 11, muted(), Paint.Align.LEFT, sans);
        pill(c, 315, 25, 54, 30, bgCard());
        coin(c, 327,40,8);
        text(c, "1 240", 345,44,10,text(),Paint.Align.CENTER,sansMedium);
    }

    private void drawSplash(Canvas c) {
        p.setShader(new LinearGradient(0,0,390,844,DEEP_GREEN,TEAL, Shader.TileMode.CLAMP));
        c.drawRect(0,0,390,844,p); p.setShader(null);
        motif(c, 195, 260, 74, GOLD, DEEP_GREEN);
        text(c,"Muslim QI",195,385,46,Color.WHITE,Paint.Align.CENTER,serif);
        text(c,"Apprends l’islam",195,425,17,Color.rgb(242,230,195),Paint.Align.CENTER,sansMedium);
        text(c,"chaque jour en t’amusant.",195,449,16,Color.rgb(242,230,195),Paint.Align.CENTER,sans);
        p.setColor(Color.argb(70,0,0,0));
        Path skyline=new Path(); skyline.moveTo(0,725); skyline.lineTo(0,640);
        skyline.quadTo(55,585,110,645); skyline.quadTo(170,560,225,645);
        skyline.quadTo(310,580,390,650); skyline.lineTo(390,844); skyline.close(); c.drawPath(skyline,p);
        star(c,195,512,9,GOLD);
        text(c,"DÉMONSTRATION DESIGN",195,790,10,Color.argb(200,255,255,255),Paint.Align.CENTER,sansMedium);
    }

    private void drawLanguage(Canvas c) {
        text(c,"Choisissez votre langue",195,105,27,text(),Paint.Align.CENTER,serif);
        text(c,"Vous pourrez la modifier à tout moment.",195,132,12,muted(),Paint.Align.CENTER,sans);
        languageCard(c,28,185,"Français","Paris • Interface complète",EMERALD);
        languageCard(c,28,300,"العربية","واجهة كاملة من اليمين إلى اليسار",GOLD);
        languageCard(c,28,415,"English","London • Full interface",TEAL);
        card(c,28,555,334,82,12,bgCard());
        text(c,"↔",52,587,22,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,"Arabe entièrement compatible RTL",78,583,12,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Mise en page, navigation et textes inversés.",78,605,10,muted(),Paint.Align.LEFT,sans);
        button(c,28,690,334,56,"Continuer",EMERALD);
        spot(28,185,362,497,WELCOME);
        spot(28,690,362,746,WELCOME);
    }

    private void languageCard(Canvas c,float x,float y,String name,String sub,int accent){
        card(c,x,y,334,92,16,bgCard());
        p.setColor(Color.argb(35,Color.red(accent),Color.green(accent),Color.blue(accent)));
        c.drawRoundRect(new RectF(x+12,y+12,x+88,y+80),14,14,p);
        motif(c,x+50,y+46,18,accent,Color.TRANSPARENT);
        text(c,name,x+110,y+42,22,text(),Paint.Align.LEFT,serif);
        text(c,sub,x+110,y+64,10,muted(),Paint.Align.LEFT,sans);
        text(c,"›",x+310,y+54,26,accent,Paint.Align.CENTER,sans);
    }

    private void drawWelcome(Canvas c) {
        text(c,"Bienvenue !",195,92,31,text(),Paint.Align.CENTER,serif);
        text(c,"Muslim QI vous accompagne chaque jour pour…",195,122,12,muted(),Paint.Align.CENTER,sans);
        valueCard(c,28,175,"Apprendre","Des contenus clairs, courts et fiables.","▣",EMERALD);
        valueCard(c,28,285,"Jouer","Des défis ludiques pour toute la famille.","◈",GOLD);
        valueCard(c,28,395,"Progresser","Un parcours positif, sans jugement.","↗",TEAL);
        text(c,"●  ○  ○",195,550,16,EMERALD,Paint.Align.CENTER,sans);
        text(c,"Passer",34,714,12,muted(),Paint.Align.LEFT,sansMedium);
        button(c,202,682,160,54,"Continuer  →",EMERALD);
        spot(202,682,362,736,ACCOUNT);
        spot(20,690,100,740,ACCOUNT);
    }

    private void valueCard(Canvas c,float x,float y,String title,String body,String icon,int accent){
        card(c,x,y,334,86,16,bgCard());
        circle(c,x+44,y+43,25,Color.argb(30,Color.red(accent),Color.green(accent),Color.blue(accent)));
        text(c,icon,x+44,y+51,24,accent,Paint.Align.CENTER,sansMedium);
        text(c,title,x+82,y+34,16,text(),Paint.Align.LEFT,sansMedium);
        text(c,body,x+82,y+57,11,muted(),Paint.Align.LEFT,sans);
    }

    private void drawAccount(Canvas c) {
        text(c,"Comment souhaitez-vous",195,92,24,text(),Paint.Align.CENTER,sansMedium);
        text(c,"continuer ?",195,120,24,text(),Paint.Align.CENTER,sansMedium);
        text(c,"Accédez immédiatement ou synchronisez plus tard.",195,147,11,muted(),Paint.Align.CENTER,sans);
        accountButton(c,30,205,"Mode invité","Explorer sans créer de compte","♙",EMERALD);
        accountButton(c,30,305,"Continuer avec Google","Synchronisation des progrès","G",Color.rgb(66,133,244));
        accountButton(c,30,405,"Continuer avec Apple","Connexion sécurisée","●",text());
        accountButton(c,30,505,"Continuer avec E-mail","Adresse et mot de passe","✉",TEAL);
        card(c,30,635,330,68,14,bgCard());
        text(c,"▣",58,675,20,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,"Tes données restent privées et contrôlables.",88,664,11,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Suppression et export prévus dans le profil.",88,684,9,muted(),Paint.Align.LEFT,sans);
        spot(30,205,360,580,HOME);
    }

    private void accountButton(Canvas c,float x,float y,String title,String sub,String icon,int accent){
        card(c,x,y,330,78,15,bgCard());
        circle(c,x+38,y+39,22,Color.argb(26,Color.red(accent),Color.green(accent),Color.blue(accent)));
        text(c,icon,x+38,y+46,19,accent,Paint.Align.CENTER,sansMedium);
        text(c,title,x+72,y+32,14,text(),Paint.Align.LEFT,sansMedium);
        text(c,sub,x+72,y+53,10,muted(),Paint.Align.LEFT,sans);
        text(c,"›",x+306,y+46,25,accent,Paint.Align.CENTER,sans);
    }

    private void drawHome(Canvas c) {
        text(c,"As-salāmu ʿalaykum,",22,48,11,muted(),Paint.Align.LEFT,sans);
        text(c,arabicPreview?"يوسف !":"Youssef ! 👋",22,76,24,text(),Paint.Align.LEFT,sansMedium);
        pill(c,305,32,63,33,bgCard()); coin(c,319,48,8);
        text(c,"1 240",342,52,10,text(),Paint.Align.CENTER,sansMedium);
        card(c,20,100,350,92,16,bgCard());
        text(c,"Prochaine prière",35,125,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Dhuhr",35,153,18,DEEP_GREEN,Paint.Align.LEFT,sansMedium);
        text(c,"12:45",283,153,22,text(),Paint.Align.CENTER,sansMedium);
        text(c,"+ 1h 02min",283,174,10,muted(),Paint.Align.CENTER,sans);
        mosque(c,342,151,0.58f);
        spot(20,100,370,192,PRAYER);
        text(c,"Ton évolution",22,219,13,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Niveau 12",22,244,15,text(),Paint.Align.LEFT,sansMedium);
        text(c,"820 / 1200 XP",286,242,9,muted(),Paint.Align.LEFT,sans);
        progress(c,22,257,300,8,0.68f,EMERALD);
        motif(c,349,238,19,GOLD,DEEP_GREEN);
        modeCard(c,20,292,168,128,"Mémoire","islamique","▣",EMERALD,MEMORY_SETUP);
        modeCard(c,202,292,168,128,"Quiz","","?",TEAL,QUIZ);
        modeCard(c,20,434,168,128,"Vrai ou faux","","✓ ×",GOLD,TRUE_FALSE);
        modeCard(c,202,434,168,128,"Devinettes","","✦",PURPLE,RIDDLE);
        card(c,20,578,350,93,16,bgCard());
        text(c,"Défi du jour",35,605,14,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Réponds à 10 questions sur les Prophètes",35,627,10,muted(),Paint.Align.LEFT,sans);
        progress(c,35,646,225,7,0.6f,EMERALD);
        text(c,"6 / 10",272,650,10,text(),Paint.Align.LEFT,sansMedium);
        gift(c,337,624);
        spot(20,578,370,671,DAILY);
        bottomNav(c,HOME);
    }

    private void modeCard(Canvas c,float x,float y,float w,float h,String t1,String t2,String icon,int color,int target){
        p.setShader(new LinearGradient(x,y,x+w,y+h,color,darken(color,0.58f),Shader.TileMode.CLAMP));
        c.drawRoundRect(new RectF(x,y,x+w,y+h),18,18,p); p.setShader(null);
        p.setColor(Color.argb(25,255,255,255));
        for(int i=0;i<4;i++) c.drawCircle(x+w-25-i*10,y+20+i*12,26+i*4,p);
        text(c,t1,x+16,y+31,17,Color.WHITE,Paint.Align.LEFT,sansMedium);
        if(!t2.isEmpty()) text(c,t2,x+16,y+51,17,Color.WHITE,Paint.Align.LEFT,sansMedium);
        circle(c,x+w-45,y+69,28,Color.argb(34,255,255,255));
        text(c,icon,x+w-45,y+77,25,Color.rgb(255,240,190),Paint.Align.CENTER,sansMedium);
        pill(c,x+14,y+h-34,57,23,Color.argb(40,255,255,255));
        text(c,"Jouer",x+42,y+h-18,10,Color.WHITE,Paint.Align.CENTER,sansMedium);
        spot(x,y,x+w,y+h,target);
    }

    private void drawPlay(Canvas c) {
        header(c,"Jouer","Choisis ton expérience aujourd’hui",false);
        text(c,"Les quatre modes",22,107,20,text(),Paint.Align.LEFT,serif);
        modeWide(c,20,135,"Mémoire islamique","Retrouve les paires et découvre une leçon.","▣",EMERALD,MEMORY_SETUP);
        modeWide(c,20,250,"Quiz","Quatre choix, une explication immédiate.","?",TEAL,QUIZ);
        modeWide(c,20,365,"Vrai ou faux","Teste rapidement ta compréhension.","✓",GOLD,TRUE_FALSE);
        modeWide(c,20,480,"Devinettes","Débloque des indices et gagne plus d’XP.","✦",PURPLE,RIDDLE);
        card(c,20,610,350,64,14,bgCard());
        text(c,"Défi quotidien",37,637,14,text(),Paint.Align.LEFT,sansMedium);
        text(c,"3 étapes • récompense spéciale",37,657,10,muted(),Paint.Align.LEFT,sans);
        gift(c,337,642);
        spot(20,610,370,674,DAILY);
        bottomNav(c,PLAY);
    }

    private void modeWide(Canvas c,float x,float y,String title,String sub,String icon,int color,int target){
        card(c,x,y,350,94,17,bgCard());
        p.setColor(color); c.drawRoundRect(new RectF(x,y,x+8,y+94),8,8,p);
        circle(c,x+52,y+47,29,Color.argb(35,Color.red(color),Color.green(color),Color.blue(color)));
        text(c,icon,x+52,y+56,27,color,Paint.Align.CENTER,sansMedium);
        text(c,title,x+94,y+38,17,text(),Paint.Align.LEFT,sansMedium);
        text(c,sub,x+94,y+61,10,muted(),Paint.Align.LEFT,sans);
        text(c,"›",x+325,y+55,28,color,Paint.Align.CENTER,sans);
        spot(x,y,x+350,y+94,target);
    }

    private void drawMemorySetup(Canvas c) {
        header(c,"Mémoire islamique","Choisis une catégorie et une difficulté",true);
        motif(c,195,110,22,GOLD,DEEP_GREEN);
        text(c,"Catégorie",22,154,14,text(),Paint.Align.LEFT,sansMedium);
        smallChoice(c,20,170,108,70,"⌂","Prophètes",true);
        smallChoice(c,141,170,108,70,"⌘","Mosquées",false);
        smallChoice(c,262,170,108,70,"▤","Villes",false);
        smallChoice(c,20,252,108,70,"♥","Valeurs",false);
        smallChoice(c,141,252,108,70,"☾","Mois hégiriens",false);
        smallChoice(c,262,252,108,70,"✦","Divers",false);
        text(c,"Difficulté",22,360,14,text(),Paint.Align.LEFT,sansMedium);
        choicePill(c,20,380,78,"Facile",true);
        choicePill(c,108,380,78,"Normal",false);
        choicePill(c,196,380,78,"Difficile",false);
        choicePill(c,284,380,86,"Expert",false);
        text(c,"Mode de jeu",22,451,14,text(),Paint.Align.LEFT,sansMedium);
        choicePill(c,20,472,166,"◷  Chronométré",true);
        choicePill(c,196,472,174,"∞  Sans limite",false);
        card(c,20,548,350,100,16,bgCard());
        text(c,"Aperçu",37,574,11,muted(),Paint.Align.LEFT,sans);
        cardBack(c,42,590,42,58);
        text(c,"6 cartes",104,603,15,text(),Paint.Align.LEFT,sansMedium);
        text(c,"3 paires • environ 1 minute",104,626,10,muted(),Paint.Align.LEFT,sans);
        button(c,20,680,350,57,"Lancer la partie",EMERALD);
        spot(20,680,370,737,MEMORY_GAME);
        bottomNav(c,PLAY);
    }

    private void smallChoice(Canvas c,float x,float y,float w,float h,String icon,String label,boolean selected){
        card(c,x,y,w,h,13,selected?DEEP_GREEN:bgCard());
        text(c,icon,x+w/2,y+29,18,selected?GOLD:EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,label,x+w/2,y+54,10,selected?Color.WHITE:text(),Paint.Align.CENTER,sansMedium);
        if(selected){ circle(c,x+w-12,y+12,9,EMERALD); text(c,"✓",x+w-12,y+16,9,Color.WHITE,Paint.Align.CENTER,sansMedium); }
    }

    private void choicePill(Canvas c,float x,float y,float w,String label,boolean selected){
        pill(c,x,y,w,42,selected?DEEP_GREEN:bgCard());
        text(c,label,x+w/2,y+26,11,selected?Color.WHITE:text(),Paint.Align.CENTER,sansMedium);
    }

    private void drawMemoryGame(Canvas c, boolean popup) {
        header(c,"","",true);
        stat(c,20,40,"01:28","Temps");
        stat(c,93,40,"12","Tentatives");
        stat(c,166,40,"1","Erreurs");
        stat(c,239,40,"2/8","Paires");
        stat(c,312,40,"240","Score");
        circle(c,350,92,18,DEEP_GREEN); text(c,"Ⅱ",350,98,15,Color.WHITE,Paint.Align.CENTER,sansMedium);
        float x0=20,y0=125,cw=78,ch=102,g=12;
        for(int r=0;r<4;r++) for(int col=0;col<4;col++){
            float x=x0+col*(cw+g), y=y0+r*(ch+g);
            int idx=r*4+col;
            if(idx==5) faceCard(c,x,y,cw,ch,"Muhammad","ﷺ",EMERALD);
            else if(idx==10) faceCard(c,x,y,cw,ch,"La Kaaba","◆",GOLD);
            else cardBack(c,x,y,cw,ch);
        }
        text(c,"Niveau 12",20,605,10,text(),Paint.Align.LEFT,sansMedium);
        text(c,"820 / 1200 XP",292,605,9,muted(),Paint.Align.LEFT,sans);
        progress(c,20,618,300,8,0.68f,EMERALD);
        motif(c,351,611,18,GOLD,DEEP_GREEN);
        if(!popup){
            card(c,20,653,350,55,14,bgCard());
            text(c,"Astuce",36,676,11,EMERALD,Paint.Align.LEFT,sansMedium);
            text(c,"Mémorise la position avant le retournement.",36,696,10,muted(),Paint.Align.LEFT,sans);
            spot(20,125,370,582,MEMORY_POPUP);
        } else {
            p.setColor(Color.argb(135,4,22,24)); c.drawRect(0,0,390,844,p);
            card(c,35,150,320,500,24,WHITE);
            circle(c,195,180,21,EMERALD); text(c,"✓",195,188,18,Color.WHITE,Paint.Align.CENTER,sansMedium);
            text(c,"Paire trouvée !",195,226,24,DEEP_GREEN,Paint.Align.CENTER,serif);
            text(c,"محمد",195,286,50,GOLD,Paint.Align.CENTER,serif);
            text(c,"Muhammad ﷺ",195,321,19,TEXT,Paint.Align.CENTER,serif);
            multiline(c,"Dernier Messager d’Allah, envoyé à toute\nl’humanité comme miséricorde.\nSon exemple guide vers la droiture.",195,365,13,MUTED,Paint.Align.CENTER,22);
            card(c,58,455,274,92,14,Color.rgb(250,246,234));
            multiline(c,"« Et Nous ne t’avons envoyé\nqu’en miséricorde pour les mondes. »",195,485,12,TEXT,Paint.Align.CENTER,20);
            text(c,"Coran 21:107",195,532,10,EMERALD,Paint.Align.CENTER,sansMedium);
            button(c,58,575,274,49,"Continuer",EMERALD);
            spot(58,575,332,624,MEMORY_RESULT);
        }
        bottomNav(c,PLAY);
    }

    private void stat(Canvas c,float x,float y,String value,String label){
        text(c,value,x+27,y,12,text(),Paint.Align.CENTER,sansMedium);
        text(c,label,x+27,y+16,8,muted(),Paint.Align.CENTER,sans);
    }

    private void drawMemoryResult(Canvas c) {
        text(c,"Partie terminée",195,82,22,text(),Paint.Align.CENTER,serif);
        badgeStars(c,195,175);
        text(c,"560",195,286,52,DEEP_GREEN,Paint.Align.CENTER,serif);
        text(c,"Ton score",195,310,11,muted(),Paint.Align.CENTER,sans);
        text(c,"Meilleur score",195,350,11,muted(),Paint.Align.CENTER,sans);
        text(c,"780",195,378,22,text(),Paint.Align.CENTER,sansMedium);
        card(c,20,415,350,78,16,bgCard());
        resultStat(c,54,440,"02:48","Temps");
        resultStat(c,137,440,"18","Tentatives");
        resultStat(c,225,440,"2","Erreurs");
        resultStat(c,317,440,"8/8","Paires");
        card(c,20,515,350,84,16,bgCard());
        text(c,"Mā shā’ Allāh !",37,544,15,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Belle concentration et belle mémoire.",37,566,10,muted(),Paint.Align.LEFT,sans);
        gift(c,334,557);
        button(c,20,635,350,55,"↻  Rejouer",EMERALD);
        outlineButton(c,20,704,350,52,"Continuer");
        spot(20,635,370,690,MEMORY_SETUP);
        spot(20,704,370,756,HOME);
        bottomNav(c,HOME);
    }

    private void resultStat(Canvas c,float x,float y,String v,String l){
        text(c,v,x,y,15,text(),Paint.Align.CENTER,sansMedium);
        text(c,l,x,y+23,9,muted(),Paint.Align.CENTER,sans);
    }

    private void drawQuiz(Canvas c) {
        header(c,"Quiz","Aqidah • Question 3/10",true);
        progress(c,20,85,350,7,0.3f,EMERALD);
        card(c,20,122,350,116,18,bgCard());
        multiline(c,"Quelle est la première parole\nrévélée au Prophète ﷺ ?",195,164,18,text(),Paint.Align.CENTER,26);
        answer(c,20,258,"A","Lis, au nom de ton Seigneur",true);
        answer(c,20,322,"B","Ô toi, enveloppé ! Lève-toi et avertis.",false);
        answer(c,20,386,"C","Dis : Il est Allah, Unique.",false);
        answer(c,20,450,"D","Craignez votre Seigneur qui vous a créés.",false);
        card(c,20,530,350,130,17,PALE_GREEN);
        text(c,"✦  Bonne réponse !",38,557,14,DEEP_GREEN,Paint.Align.LEFT,sansMedium);
        multiline(c,"La première révélation commence par « Lis ».\nLa réponse est expliquée sans interrompre le jeu.",38,586,11,TEXT,Paint.Align.LEFT,19);
        text(c,"▣  Sourate Al-‘Alaq (96:1)",38,640,10,EMERALD,Paint.Align.LEFT,sansMedium);
        button(c,20,690,350,54,"Question suivante  →",EMERALD);
        bottomNav(c,PLAY);
    }

    private void answer(Canvas c,float x,float y,String l,String body,boolean selected){
        card(c,x,y,350,52,13,selected?Color.rgb(239,249,244):bgCard());
        if(selected){ p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(2); p.setColor(EMERALD); c.drawRoundRect(new RectF(x,y,x+350,y+52),13,13,p); p.setStyle(Paint.Style.FILL); }
        circle(c,x+28,y+26,15,selected?EMERALD:Color.argb(25,12,61,74));
        text(c,l,x+28,y+31,11,selected?Color.WHITE:text(),Paint.Align.CENTER,sansMedium);
        text(c,body,x+53,y+31,11,text(),Paint.Align.LEFT,sans);
        if(selected) text(c,"✓",x+325,y+31,14,EMERALD,Paint.Align.CENTER,sansMedium);
    }

    private void drawTrueFalse(Canvas c) {
        header(c,"Vrai ou faux","Fiqh • Série rapide",true);
        pill(c,145,90,100,38,Color.rgb(250,240,207));
        text(c,"⌛ 00:20",195,115,13,Color.rgb(151,108,15),Paint.Align.CENTER,sansMedium);
        card(c,24,160,342,205,20,bgCard());
        multiline(c,"Les ablutions sont invalidées\npar le toucher entre\nhomme et femme.",195,226,21,text(),Paint.Align.CENTER,31);
        p.setShader(new LinearGradient(24,392,178,525,EMERALD,DEEP_GREEN,Shader.TileMode.CLAMP));
        c.drawRoundRect(new RectF(24,392,178,525),20,20,p); p.setShader(null);
        text(c,"Vrai",101,445,20,Color.WHITE,Paint.Align.CENTER,sansMedium);
        text(c,"✓",101,490,36,Color.WHITE,Paint.Align.CENTER,sansMedium);
        p.setShader(new LinearGradient(212,392,366,525,Color.rgb(208,92,72),RED,Shader.TileMode.CLAMP));
        c.drawRoundRect(new RectF(212,392,366,525),20,20,p); p.setShader(null);
        text(c,"Faux",289,445,20,Color.WHITE,Paint.Align.CENTER,sansMedium);
        text(c,"×",289,490,40,Color.WHITE,Paint.Align.CENTER,sansMedium);
        card(c,20,552,350,118,17,PALE_GREEN);
        text(c,"✦  Bonne réponse !",38,579,14,DEEP_GREEN,Paint.Align.LEFT,sansMedium);
        multiline(c,"Le jeu affiche ensuite une explication courte,\nune source et un encouragement positif.",38,609,11,TEXT,Paint.Align.LEFT,19);
        text(c,"▣  Référence vérifiée",38,651,10,EMERALD,Paint.Align.LEFT,sansMedium);
        button(c,20,697,350,54,"Question suivante  →",EMERALD);
        bottomNav(c,PLAY);
    }

    private void drawRiddle(Canvas c) {
        header(c,"Devinettes","Bonus rapidité +20 XP",true);
        pill(c,142,90,106,34,PURPLE); text(c,"Indice 1 / 3",195,112,11,Color.WHITE,Paint.Align.CENTER,sansMedium);
        card(c,24,146,342,155,20,Color.rgb(248,246,252));
        multiline(c,"Je suis la première sourate\ndu Coran et j’ouvre chaque prière.",195,202,19,Color.rgb(52,42,74),Paint.Align.CENTER,28);
        clue(c,24,324,"Indice 2","−10 XP",false);
        clue(c,24,376,"Indice 3","−20 XP",false);
        text(c,"Choisis ta réponse",24,455,13,text(),Paint.Align.LEFT,sansMedium);
        answerTile(c,24,475,"Al-Falaq",false);
        answerTile(c,203,475,"Al-Ikhlās",false);
        answerTile(c,24,539,"Al-Fātiḥah",true);
        answerTile(c,203,539,"An-Nās",false);
        card(c,24,620,342,82,16,Color.rgb(244,240,251));
        text(c,"✦  Excellent !",42,647,14,PURPLE,Paint.Align.LEFT,sansMedium);
        text(c,"Trouvé avec 1 indice • +20 XP",42,674,11,Color.rgb(52,42,74),Paint.Align.LEFT,sans);
        button(c,24,724,342,52,"Question suivante  →",PURPLE);
        bottomNav(c,PLAY);
    }

    private void clue(Canvas c,float x,float y,String title,String xp,boolean open){
        card(c,x,y,342,40,12,bgCard());
        text(c,title,x+20,y+25,11,PURPLE,Paint.Align.LEFT,sansMedium);
        text(c,xp,x+280,y+25,10,muted(),Paint.Align.CENTER,sans);
        text(c,open?"":"▣",x+322,y+25,12,PURPLE,Paint.Align.CENTER,sans);
    }

    private void answerTile(Canvas c,float x,float y,String label,boolean selected){
        card(c,x,y,163,50,13,selected?Color.rgb(244,240,251):bgCard());
        if(selected){ p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(2);p.setColor(PURPLE);c.drawRoundRect(new RectF(x,y,x+163,y+50),13,13,p);p.setStyle(Paint.Style.FILL); }
        text(c,label,x+81.5f,y+31,12,selected?PURPLE:text(),Paint.Align.CENTER,sansMedium);
        if(selected) text(c,"✓",x+143,y+31,12,PURPLE,Paint.Align.CENTER,sansMedium);
    }

    private void drawDaily(Canvas c) {
        header(c,"Défi du jour","Dimanche 2 août 2026",true);
        card(c,20,92,350,124,20,bgCard());
        badgeStars(c,72,149);
        text(c,"Défi du jour complété !",120,127,17,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Mā shā’ Allāh, bel effort aujourd’hui.",120,151,10,muted(),Paint.Align.LEFT,sans);
        text(c,"08 min 45",120,181,12,text(),Paint.Align.LEFT,sansMedium);
        text(c,"820 XP",290,181,12,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,"Résumé de tes performances",22,252,15,text(),Paint.Align.LEFT,sansMedium);
        challengeRow(c,20,270,"?","Quiz","7/10 bonnes réponses","+350 XP",TEAL,0.7f);
        challengeRow(c,20,350,"✓","Vrai ou faux","5/6 bonnes réponses","+250 XP",EMERALD,0.83f);
        challengeRow(c,20,430,"✦","Devinettes","Trouvé avec 2 indices","+200 XP",PURPLE,0.62f);
        card(c,20,530,350,83,17,bgCard());
        motif(c,58,570,21,GOLD,DEEP_GREEN);
        text(c,"Récompense du jour",95,555,11,muted(),Paint.Align.LEFT,sans);
        text(c,"Badge Connaissance",95,580,15,text(),Paint.Align.LEFT,sansMedium);
        card(c,20,630,350,72,17,bgCard());
        text(c,"🔥",46,666,25,text(),Paint.Align.CENTER,sans);
        text(c,"7 jours",76,658,18,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Série actuelle",76,680,10,muted(),Paint.Align.LEFT,sans);
        for(int i=0;i<7;i++){ circle(c,225+i*19,666,7,i<6?EMERALD:SAND); if(i<6) text(c,"✓",225+i*19,669,6,Color.WHITE,Paint.Align.CENTER,sansMedium); }
        outlineButton(c,20,724,165,52,"Historique");
        button(c,195,724,175,52,"Nouveau défi",EMERALD);
        bottomNav(c,HOME);
    }

    private void challengeRow(Canvas c,float x,float y,String icon,String title,String sub,String xp,int color,float value){
        card(c,x,y,350,65,14,bgCard());
        circle(c,x+32,y+32,22,Color.argb(34,Color.red(color),Color.green(color),Color.blue(color)));
        text(c,icon,x+32,y+39,20,color,Paint.Align.CENTER,sansMedium);
        text(c,title,x+66,y+24,14,text(),Paint.Align.LEFT,sansMedium);
        text(c,sub,x+66,y+43,9,muted(),Paint.Align.LEFT,sans);
        text(c,xp,x+310,y+24,10,color,Paint.Align.CENTER,sansMedium);
        progress(c,x+66,y+51,230,5,value,color);
    }

    private void drawProgress(Canvas c) {
        header(c,"Mon chemin de connaissance","Chaque pas nourrit ta curiosité",false);
        card(c,20,90,350,118,18,bgCard());
        text(c,"Niveau actuel",38,118,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Niveau 12",38,150,25,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Chercheur de science",38,171,11,muted(),Paint.Align.LEFT,sans);
        motif(c,325,145,28,GOLD,DEEP_GREEN);
        progress(c,38,188,250,8,0.68f,EMERALD);
        text(c,"820 / 1200 XP",298,192,8,muted(),Paint.Align.LEFT,sans);
        text(c,"Maîtrise par catégorie",22,242,14,text(),Paint.Align.LEFT,sansMedium);
        ring(c,53,290,26,0.80f,"80%","Coran",EMERALD);
        ring(c,124,290,26,0.65f,"65%","Aqidah",GOLD);
        ring(c,195,290,26,0.72f,"72%","Sīrah",TEAL);
        ring(c,266,290,26,0.58f,"58%","Fiqh",PURPLE);
        ring(c,337,290,26,0.90f,"90%","Valeurs",EMERALD);
        card(c,20,350,350,86,17,bgCard());
        text(c,"🔥",50,390,28,text(),Paint.Align.CENTER,sans);
        text(c,"7 jours consécutifs",80,380,16,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Meilleur record : 12 jours",80,405,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Réponses correctes",22,471,14,text(),Paint.Align.LEFT,sansMedium);
        card(c,20,490,350,78,17,bgCard());
        ring(c,60,529,26,0.85f,"85%","",EMERALD);
        text(c,"Très bien !",100,521,16,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Tu progresses régulièrement.",100,545,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Sujets à revoir",22,605,14,text(),Paint.Align.LEFT,sansMedium);
        subjectRow(c,20,624,"Les piliers de la foi","Aqidah • 8 questions","40%");
        subjectRow(c,20,683,"Les ablutions","Fiqh • 6 questions","60%");
        bottomNav(c,PROGRESS);
        spot(220,90,370,208,BADGES);
    }

    private void subjectRow(Canvas c,float x,float y,String title,String sub,String value){
        card(c,x,y,350,50,12,bgCard());
        circle(c,x+25,y+25,15,PALE_GREEN);
        text(c,"▣",x+25,y+30,12,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,title,x+50,y+21,11,text(),Paint.Align.LEFT,sansMedium);
        text(c,sub,x+50,y+38,8,muted(),Paint.Align.LEFT,sans);
        text(c,value,x+318,y+30,10,EMERALD,Paint.Align.CENTER,sansMedium);
    }

    private void drawBadges(Canvas c) {
        header(c,"Mes badges","Célèbre chaque étape de ton apprentissage",true);
        choicePill(c,20,86,78,"Tous",true);
        choicePill(c,108,86,85,"Progression",false);
        choicePill(c,203,86,72,"Spécial",false);
        choicePill(c,285,86,85,"Défis",false);
        badgeCard(c,20,148,"Première paire","Joue ton premier match","◇",EMERALD,false);
        badgeCard(c,202,148,"Mémoire exceptionnelle","100 réponses correctes","✹",TEAL,false);
        badgeCard(c,20,320,"Sept jours","Apprends 7 jours d’affilée","7",GOLD,false);
        badgeCard(c,202,320,"Sans faute","20 réponses d’affilée","✓",EMERALD,false);
        badgeCard(c,20,492,"Érudit débutant","Atteins le niveau 5","▣",PURPLE,false);
        badgeCard(c,202,492,"Persévérant","Atteins le niveau 20","▣",MUTED,true);
        bottomNav(c,PROGRESS);
    }

    private void badgeCard(Canvas c,float x,float y,String title,String sub,String icon,int color,boolean locked){
        card(c,x,y,168,154,17,bgCard());
        motif(c,x+84,y+53,31,locked?Color.GRAY:GOLD,locked?Color.DKGRAY:color);
        text(c,locked?"▣":icon,x+84,y+61,24,Color.WHITE,Paint.Align.CENTER,sansMedium);
        text(c,title,x+84,y+106,11,locked?muted():text(),Paint.Align.CENTER,sansMedium);
        multiline(c,sub,x+84,y+126,9,muted(),Paint.Align.CENTER,14);
    }

    private void drawLeaderboard(Canvas c) {
        header(c,"Classement","Progresse ensemble, avec bienveillance",false);
        card(c,20,88,350,57,14,Color.rgb(235,245,240));
        text(c,"ⓘ",42,123,17,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,"Le classement est facultatif.",68,112,11,TEXT,Paint.Align.LEFT,sansMedium);
        text(c,"Tes progrès personnels comptent avant tout.",68,131,9,MUTED,Paint.Align.LEFT,sans);
        choicePill(c,20,164,78,"Semaine",true);
        choicePill(c,108,164,78,"Mois",false);
        choicePill(c,196,164,78,"Amis",false);
        choicePill(c,284,164,86,"Tous",false);
        podium(c);
        rankRow(c,20,420,"4","Fatima","820 XP",false);
        rankRow(c,20,478,"5","Hassan","720 XP",false);
        rankRow(c,20,536,"6","Toi","820 XP",true);
        rankRow(c,20,594,"7","Ibrahim","610 XP",false);
        rankRow(c,20,652,"8","Mariam","560 XP",false);
        bottomNav(c,LEADERBOARD);
    }

    private void podium(Canvas c){
        circle(c,88,263,26,SAND); text(c,"A",88,270,18,TEXT,Paint.Align.CENTER,sansMedium);
        circle(c,195,235,31,Color.rgb(248,224,142)); text(c,"Y",195,243,21,TEXT,Paint.Align.CENTER,sansMedium);
        circle(c,302,274,26,Color.rgb(226,184,142)); text(c,"A",302,281,18,TEXT,Paint.Align.CENTER,sansMedium);
        p.setColor(Color.rgb(223,228,225)); c.drawRoundRect(new RectF(54,308,122,387),10,10,p);
        p.setColor(Color.rgb(246,216,124)); c.drawRoundRect(new RectF(151,285,239,387),10,10,p);
        p.setColor(Color.rgb(222,174,130)); c.drawRoundRect(new RectF(268,321,336,387),10,10,p);
        text(c,"2",88,349,28,TEXT,Paint.Align.CENTER,serif);
        text(c,"1",195,334,33,TEXT,Paint.Align.CENTER,serif);
        text(c,"3",302,357,28,TEXT,Paint.Align.CENTER,serif);
        text(c,"Amine",88,293,10,text(),Paint.Align.CENTER,sansMedium);
        text(c,"Youssef",195,278,10,text(),Paint.Align.CENTER,sansMedium);
        text(c,"Aïcha",302,304,10,text(),Paint.Align.CENTER,sansMedium);
    }

    private void rankRow(Canvas c,float x,float y,String rank,String name,String xp,boolean self){
        card(c,x,y,350,48,12,self?Color.rgb(250,244,220):bgCard());
        if(self){ p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.5f);p.setColor(GOLD);c.drawRoundRect(new RectF(x,y,x+350,y+48),12,12,p);p.setStyle(Paint.Style.FILL);}
        text(c,rank,x+25,y+30,12,text(),Paint.Align.CENTER,sansMedium);
        circle(c,x+60,y+24,15,self?DEEP_GREEN:SAND);
        text(c,self?"Q":name.substring(0,1),x+60,y+29,11,self?Color.WHITE:TEXT,Paint.Align.CENTER,sansMedium);
        text(c,name,x+90,y+29,12,text(),Paint.Align.LEFT,sansMedium);
        text(c,xp,x+302,y+29,10,self?EMERALD:muted(),Paint.Align.CENTER,sansMedium);
    }

    private void drawProfile(Canvas c) {
        header(c,"Profil & paramètres","Personnalise ton expérience",false);
        card(c,20,88,350,93,17,bgCard());
        circle(c,68,134,32,Color.rgb(223,226,222));
        text(c,"♙",68,144,29,MUTED,Paint.Align.CENTER,sans);
        text(c,"Youssef",114,122,17,text(),Paint.Align.LEFT,sansMedium);
        text(c,"yo***@exemple.com",114,145,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Niveau 12",114,165,10,EMERALD,Paint.Align.LEFT,sansMedium);
        motif(c,337,134,20,GOLD,DEEP_GREEN);
        text(c,"Préférences",22,218,13,text(),Paint.Align.LEFT,sansMedium);
        setting(c,20,234,"◎","Langue",arabicPreview?"العربية":"Français",false);
        setting(c,20,282,"◐","Mode sombre","",true);
        setting(c,20,330,"♫","Sons","",true);
        setting(c,20,378,"≈","Vibration","",true);
        setting(c,20,426,"Aa","Taille du texte","Moyenne",false);
        text(c,"Contenu & expérience",22,502,13,text(),Paint.Align.LEFT,sansMedium);
        setting(c,20,518,"♛","Acheter sans publicité","",false);
        setting(c,20,566,"⌂","Afficher le widget prière","",true);
        text(c,"Données & sécurité",22,638,13,text(),Paint.Align.LEFT,sansMedium);
        setting(c,20,654,"⇩","Exporter mes données","",false);
        setting(c,20,702,"!","Signaler une erreur","",false);
        spot(20,282,370,330,PROFILE);
        spot(20,234,370,282,PROFILE);
        bottomNav(c,PROFILE);
    }

    private void setting(Canvas c,float x,float y,String icon,String label,String value,boolean toggle){
        card(c,x,y,350,43,9,bgCard());
        text(c,icon,x+22,y+27,13,EMERALD,Paint.Align.CENTER,sansMedium);
        text(c,label,x+45,y+27,11,text(),Paint.Align.LEFT,sans);
        if(toggle){
            boolean on = !label.equals("Mode sombre") || dark;
            pill(c,x+300,y+11,35,21,on?EMERALD:Color.rgb(211,215,211));
            circle(c,on?x+325:x+310,y+21.5f,8,Color.WHITE);
        } else {
            text(c,value,x+300,y+27,10,muted(),Paint.Align.RIGHT,sans);
            text(c,"›",x+330,y+28,18,muted(),Paint.Align.CENTER,sans);
        }
    }

    private void drawPrayer(Canvas c) {
        header(c,"Prochaine prière","Localisation facultative",true);
        p.setShader(new LinearGradient(20,92,370,300,DEEP_GREEN,TEAL,Shader.TileMode.CLAMP));
        c.drawRoundRect(new RectF(20,92,370,300),24,24,p); p.setShader(null);
        text(c,"Dhuhr",42,139,25,Color.WHITE,Paint.Align.LEFT,serif);
        text(c,"12:45",42,190,46,Color.WHITE,Paint.Align.LEFT,sansMedium);
        text(c,"dans 1 h 02 min",42,218,13,Color.rgb(220,236,229),Paint.Align.LEFT,sans);
        mosque(c,303,203,1.15f);
        text(c,"Méthode active",22,342,14,text(),Paint.Align.LEFT,sansMedium);
        card(c,20,360,350,70,15,bgCard());
        text(c,"Muslim World League",38,388,14,text(),Paint.Align.LEFT,sansMedium);
        text(c,"Angles ajustables dans les paramètres",38,411,10,muted(),Paint.Align.LEFT,sans);
        text(c,"Aujourd’hui",22,472,14,text(),Paint.Align.LEFT,sansMedium);
        prayerRow(c,20,490,"Fajr","05:22",false);
        prayerRow(c,20,545,"Dhuhr","12:45",true);
        prayerRow(c,20,600,"Asr","16:38",false);
        prayerRow(c,20,655,"Maghrib","20:24",false);
        prayerRow(c,20,710,"Isha","21:58",false);
        bottomNav(c,HOME);
    }

    private void prayerRow(Canvas c,float x,float y,String name,String time,boolean next){
        card(c,x,y,350,45,11,next?Color.rgb(232,245,238):bgCard());
        text(c,name,x+20,y+28,12,next?DEEP_GREEN:text(),Paint.Align.LEFT,sansMedium);
        text(c,time,x+316,y+28,12,next?EMERALD:text(),Paint.Align.RIGHT,sansMedium);
        if(next) circle(c,x+286,y+22,5,EMERALD);
    }

    private void bottomNav(Canvas c,int active){
        float y=782;
        p.setShadowLayer(12,0,-2,Color.argb(35,0,0,0));
        card(c,12,y,366,56,20,bgCard());
        p.clearShadowLayer();
        int[] targets={HOME,PLAY,PROGRESS,LEADERBOARD,PROFILE};
        String[] icons={"⌂","◇","▥","♛","♙"};
        String[] labels={"Accueil","Jouer","Progression","Classement","Profil"};
        for(int i=0;i<5;i++){
            float x=48+i*73.5f;
            boolean on=active==targets[i] || (active==BADGES&&targets[i]==PROGRESS);
            text(c,icons[i],x,y+23,16,on?EMERALD:muted(),Paint.Align.CENTER,sansMedium);
            text(c,labels[i],x,y+43,7.5f,on?DEEP_GREEN:muted(),Paint.Align.CENTER,on?sansMedium:sans);
            spot(x-34,y,x+34,y+56,targets[i]);
        }
    }

    @Override public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() != MotionEvent.ACTION_UP) return true;
        float x=e.getX()/sx, y=e.getY()/sy;
        if(screen==PROFILE && y>=282 && y<=330){
            dark=!dark; invalidate(); return true;
        }
        if(screen==PROFILE && y>=234 && y<=282){
            arabicPreview=!arabicPreview; invalidate(); return true;
        }
        for(int i=spots.size()-1;i>=0;i--){
            Hotspot h=spots.get(i);
            if(h.r.contains(x,y)){ screen=h.target; invalidate(); return true; }
        }
        return true;
    }

    private void spot(float l,float t,float r,float b,int target){ spots.add(new Hotspot(new RectF(l,t,r,b),target)); }

    private void card(Canvas c,float x,float y,float w,float h,float radius,int color){
        p.setStyle(Paint.Style.FILL);
        p.setColor(color);
        p.setShadowLayer(7,0,3,Color.argb(dark?60:25,0,0,0));
        c.drawRoundRect(new RectF(x,y,x+w,y+h),radius,radius,p);
        p.clearShadowLayer();
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1);p.setColor(dark?Color.argb(35,255,255,255):Color.argb(24,6,95,87));
        c.drawRoundRect(new RectF(x,y,x+w,y+h),radius,radius,p);p.setStyle(Paint.Style.FILL);
    }

    private void pill(Canvas c,float x,float y,float w,float h,int color){
        p.setColor(color);p.setStyle(Paint.Style.FILL);c.drawRoundRect(new RectF(x,y,x+w,y+h),h/2,h/2,p);
    }

    private void button(Canvas c,float x,float y,float w,float h,String label,int color){
        p.setShader(new LinearGradient(x,y,x+w,y+h,color,darken(color,.72f),Shader.TileMode.CLAMP));
        p.setShadowLayer(7,0,3,Color.argb(35,0,0,0)); c.drawRoundRect(new RectF(x,y,x+w,y+h),14,14,p);p.clearShadowLayer();p.setShader(null);
        text(c,label,x+w/2,y+h/2+5,13,Color.WHITE,Paint.Align.CENTER,sansMedium);
    }

    private void outlineButton(Canvas c,float x,float y,float w,float h,String label){
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.2f);p.setColor(Color.argb(70,6,95,87));c.drawRoundRect(new RectF(x,y,x+w,y+h),14,14,p);p.setStyle(Paint.Style.FILL);
        text(c,label,x+w/2,y+h/2+5,13,text(),Paint.Align.CENTER,sansMedium);
    }

    private void circle(Canvas c,float x,float y,float r,int color){ p.setStyle(Paint.Style.FILL);p.setColor(color);c.drawCircle(x,y,r,p); }

    private void text(Canvas c,String s,float x,float y,float size,int color,Paint.Align align,Typeface face){
        p.setShader(null);p.setStyle(Paint.Style.FILL);p.setColor(color);p.setTextSize(size);p.setTextAlign(align);p.setTypeface(face);c.drawText(s,x,y,p);
    }

    private void multiline(Canvas c,String s,float x,float y,float size,int color,Paint.Align align,float line){
        String[] lines=s.split("\\n"); for(int i=0;i<lines.length;i++) text(c,lines[i],x,y+i*line,size,color,align,sans);
    }

    private void progress(Canvas c,float x,float y,float w,float h,float value,int color){
        pill(c,x,y,w,h,dark?Color.rgb(52,69,67):Color.rgb(226,223,211));
        pill(c,x,y,w*value,h,color);
    }

    private void coin(Canvas c,float x,float y,float r){
        circle(c,x,y,r,GOLD); circle(c,x,y,r-3,Color.rgb(245,215,115)); text(c,"•",x,y+3,8,Color.rgb(154,107,15),Paint.Align.CENTER,sansMedium);
    }

    private void motif(Canvas c,float cx,float cy,float r,int outer,int inner){
        Path path=new Path();
        for(int i=0;i<16;i++){
            double a=-Math.PI/2+i*Math.PI/8;
            float rr=(i%2==0)?r:r*.72f;
            float x=cx+(float)Math.cos(a)*rr, y=cy+(float)Math.sin(a)*rr;
            if(i==0) path.moveTo(x,y); else path.lineTo(x,y);
        }
        path.close(); p.setColor(outer);p.setStyle(Paint.Style.FILL);c.drawPath(path,p);
        circle(c,cx,cy,r*.63f,inner==Color.TRANSPARENT?Color.argb(24,255,255,255):inner);
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.3f);p.setColor(outer);
        c.drawCircle(cx,cy,r*.43f,p);c.drawLine(cx-r*.30f,cy,cx+r*.30f,cy,p);c.drawLine(cx,cy-r*.30f,cx,cy+r*.30f,p);p.setStyle(Paint.Style.FILL);
    }

    private void cardBack(Canvas c,float x,float y,float w,float h){
        p.setShader(new LinearGradient(x,y,x+w,y+h,DEEP_GREEN,TEAL,Shader.TileMode.CLAMP));
        p.setShadowLayer(5,0,2,Color.argb(32,0,0,0));c.drawRoundRect(new RectF(x,y,x+w,y+h),9,9,p);p.clearShadowLayer();p.setShader(null);
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(1.4f);p.setColor(GOLD);c.drawRoundRect(new RectF(x+3,y+3,x+w-3,y+h-3),7,7,p);p.setStyle(Paint.Style.FILL);
        motif(c,x+w/2,y+h/2,Math.min(w,h)*.18f,GOLD,DEEP_GREEN);
    }

    private void faceCard(Canvas c,float x,float y,float w,float h,String title,String icon,int color){
        card(c,x,y,w,h,9,WHITE);
        text(c,icon,x+w/2,y+h*.43f,Math.min(w,h)*.23f,color,Paint.Align.CENTER,serif);
        text(c,title,x+w/2,y+h*.74f,title.length()>8?7.5f:9f,TEXT,Paint.Align.CENTER,sansMedium);
    }

    private void star(Canvas c,float cx,float cy,float r,int color){
        Path path=new Path(); for(int i=0;i<10;i++){ double a=-Math.PI/2+i*Math.PI/5;float rr=(i%2==0)?r:r*.42f;float x=cx+(float)Math.cos(a)*rr,y=cy+(float)Math.sin(a)*rr;if(i==0)path.moveTo(x,y);else path.lineTo(x,y);}path.close();p.setColor(color);c.drawPath(path,p);
    }

    private void badgeStars(Canvas c,float cx,float cy){
        motif(c,cx,cy,48,GOLD,DEEP_GREEN);
        star(c,cx-20,cy+3,14,GOLD);star(c,cx,cy-8,18,Color.rgb(249,218,112));star(c,cx+21,cy+3,14,GOLD);
    }

    private void ring(Canvas c,float cx,float cy,float r,float value,String percent,String label,int color){
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(5);p.setStrokeCap(Paint.Cap.ROUND);p.setColor(dark?Color.rgb(57,70,68):Color.rgb(225,222,210));c.drawCircle(cx,cy,r,p);
        p.setColor(color);c.drawArc(new RectF(cx-r,cy-r,cx+r,cy+r),-90,360*value,false,p);p.setStrokeCap(Paint.Cap.BUTT);p.setStyle(Paint.Style.FILL);
        text(c,percent,cx,cy+4,10,text(),Paint.Align.CENTER,sansMedium);text(c,label,cx,cy+r+18,8,muted(),Paint.Align.CENTER,sans);
    }

    private void mosque(Canvas c,float x,float y,float scale){
        p.setColor(Color.rgb(47,141,82));
        c.drawRect(x-35*scale,y,x+35*scale,y+25*scale,p);
        c.drawCircle(x,y,24*scale,p);
        p.setColor(GOLD);c.drawRect(x-2*scale,y-35*scale,x+2*scale,y-8*scale,p);star(c,x,y-39*scale,4*scale,GOLD);
        p.setColor(Color.rgb(34,106,66));c.drawRect(x+29*scale,y-35*scale,x+35*scale,y+25*scale,p);c.drawCircle(x+32*scale,y-35*scale,5*scale,p);
    }

    private void gift(Canvas c,float x,float y){
        p.setColor(GOLD);c.drawRoundRect(new RectF(x-16,y-6,x+16,y+18),4,4,p);p.setColor(DEEP_GREEN);c.drawRect(x-3,y-9,x+3,y+18,p);c.drawRect(x-18,y-5,x+18,y+1,p);
        p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(3);p.setColor(GOLD);c.drawOval(new RectF(x-16,y-17,x-1,y-4),p);c.drawOval(new RectF(x+1,y-17,x+16,y-4),p);p.setStyle(Paint.Style.FILL);
    }

    private int darken(int color,float factor){
        return Color.rgb(Math.max(0,(int)(Color.red(color)*factor)),Math.max(0,(int)(Color.green(color)*factor)),Math.max(0,(int)(Color.blue(color)*factor)));
    }
}
