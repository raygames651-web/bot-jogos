# QUIZ PREMIADO TOP - Android App

## Estrutura

```
jogo-top/
├── AndroidManifest.xml
├── java/com/jogotop/quizpremiado/
│   ├── MainActivity.java
│   └── ResultActivity.java
├── res/layout/
│   ├── activity_main.xml
│   └── activity_result.xml
└── README.md
```

---

## Integração de Anúncios

### 1. AdMob (Google)

**build.gradle (app):**
```gradle
dependencies {
    implementation 'com.google.android.gms:play-services-ads:22.x.x'
}
```

**AndroidManifest.xml:**
```xml
<uses-permission android:name="com.google.android.gms.permission.AD_ID"/>

<meta-data
    android:name="com.google.android.gms.ads.APPLICATION_ID"
    android:value="ca-app-pub-XXXXXXXX~XXXXXXXX"/>
```

**No código (MainActivity.java):**
```java
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

// Inicializar
MobileAds.initialize(this, "CAAD-XXXXXX");

// Criar banner
AdView adView = new AdView(this);
adView.setAdUnitId("ca-app-pub-XXXXX/XXXXXX");
LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.MATCH_PARENT,
    LinearLayout.LayoutParams.WRAP_CONTENT
);
adView.setLayoutParams(params);

// Carregar anúncio
AdRequest adRequest = new AdRequest.Builder().build();
adView.loadAd(adRequest);

// Adicionar ao container
adContainerTop.addView(adView);
```

---

### 2. Unity Ads

```gradle
implementation 'com.unity3d.ads:unity-ads:4.x.x'
```

---

### 3. AppLovin

```gradle
implementation 'com.applovin:applovin-sdk:11.x.x'
```

---

## Posicionamento dos Anúncios

| Local | Tamanho | Tipo |
|-------|---------|------|
| Topo | 320x50 | Banner |
| Rodapé | 320x50 | Banner |
| Entre perguntas | 300x250 | Interstitial |
| Vídeo reward | Full | Rewarded |

---

## Compile

1. Abrir no Android Studio
2. Build → Build APK
3. APK gerado em `app/build/outputs/apk/debug/`

---

## Monetização Recomendada

1. **Banner** - Topo e rodapé (fixo)
2. **Interstitial** - Entre cada 3 perguntas
3. **Rewarded** - Ver vídeo = +1 жизнь ou dica

Boa sorte! 🎮💰