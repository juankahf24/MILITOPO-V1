#!/usr/bin/env bash
set -euo pipefail

APK="muslim-qi-design/app/build/outputs/apk/debug/app-debug.apk"
PACKAGE="com.muslimqi.design.demo"
ACTIVITY="com.muslimqi.design.PremiumMainActivity"

adb install -r "$APK"
adb shell am force-stop "$PACKAGE"
adb logcat -c
adb shell am start -W -n "$PACKAGE/$ACTIVITY"
sleep 5

# Some hosted Pixel images display a launcher ANR over the foreground app.
# This tap closes only that system dialog when it appears; otherwise it is harmless.
adb shell input tap 410 1590
sleep 1

adb exec-out screencap -p > muslim-qi-language-screen.png
adb logcat -d -v threadtime > muslim-qi-logcat.txt

APP_PID="$(adb shell pidof "$PACKAGE" | tr -d '\r')"
echo "Muslim QI process: $APP_PID"
if [[ -z "$APP_PID" ]]; then
  echo "Application process stopped. Relevant crash log:"
  grep -A 100 -B 20 -E 'FATAL EXCEPTION|AndroidRuntime|Process: com.muslimqi.design.demo' muslim-qi-logcat.txt || true
  exit 1
fi

adb shell dumpsys activity activities | grep "$PACKAGE/$ACTIVITY"
test -s muslim-qi-language-screen.png

# Pixel 6 profile: tap the bottom action button on the language screen.
adb shell input tap 540 2190
sleep 2
SECOND_PID="$(adb shell pidof "$PACKAGE" | tr -d '\r')"
test -n "$SECOND_PID"
adb shell uiautomator dump /sdcard/muslim-qi-ui.xml >/dev/null
adb shell cat /sdcard/muslim-qi-ui.xml > muslim-qi-ui.xml
grep -q "Comprendre avec des explications simples" muslim-qi-ui.xml
adb exec-out screencap -p > muslim-qi-after-interaction.png
test -s muslim-qi-after-interaction.png

echo "Premium Muslim QI launch and onboarding interaction smoke test passed."
