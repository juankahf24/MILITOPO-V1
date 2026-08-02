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

# Pixel 6 profile: tap the fixed bottom action button on the language screen.
adb shell input tap 540 2150
sleep 2
SECOND_PID="$(adb shell pidof "$PACKAGE" | tr -d '\r')"
test -n "$SECOND_PID"
adb exec-out screencap -p > muslim-qi-after-interaction.png
test -s muslim-qi-after-interaction.png

echo "Premium Muslim QI launch and onboarding interaction smoke test passed."
