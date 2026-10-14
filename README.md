# SubTrack - Abonelik ve Gider Takip Uygulaması 📱

SubTrack, kullanıcıların aylık aboneliklerini (Netflix, Spotify, spor salonu vb.) ve düzenli giderlerini takip etmelerini, toplam harcamalarını görmelerini ve ödeme günleri yaklaştığında bildirim almalarını sağlayan modern bir Android uygulamasıdır.

## 🚀 Özellikler

* **Abonelik Yönetimi:** Yeni abonelik ekleme ve silme.
* **Gider Hesaplama:** Ana ekranda aylık toplam abonelik masrafının anlık olarak hesaplanması ve gösterimi.
* **Kilit Ekranı Bildirimleri:** AlarmManager kullanılarak ödeme günü yaklaşan abonelikler için yerel bildirim (Local Notification) gönderimi.
* **Çevrimdışı Çalışma:** Tüm veriler cihazda yerel olarak (Room Database) tutulur, internet gerektirmez.
* **Modern Arayüz:** Jetpack Compose ile tasarlanmış, akıcı ve modern kullanıcı arayüzü.

## 🛠️ Kullanılan Teknolojiler ve Mimari

Bu proje, modern Android geliştirme standartlarına uygun olarak geliştirilmiştir:

* **Dil:** Kotlin
* **Kullanıcı Arayüzü (UI):** Jetpack Compose (Declarative UI)
* **Mimari:** MVVM (Model-View-ViewModel)
* **Yerel Veritabanı:** Room Database
* **Asenkron İşlemler:** Kotlin Coroutines & Flow
* **Zamanlanmış Görevler:** AlarmManager & BroadcastReceiver (Bildirimler için)
* **Sayfa Geçişleri:** Jetpack Compose Navigation

## 📂 Proje Yapısı (MVVM)

Proje, temiz mimari (Clean Architecture) prensiplerine uygun olarak paketlere ayrılmıştır:

* `data/`: Veritabanı yapılandırması, Entity (Tablo) sınıfları, DAO arayüzleri ve Repository.
* `ui/`: Jetpack Compose kullanılarak oluşturulan ekranlar (DashboardScreen, AddSubscriptionScreen).
* `viewmodel/`: Arayüz ile veri katmanı arasındaki iletişimi sağlayan ViewModel sınıfları.
* `receiver/`: Arka planda çalışan bildirim dinleyicileri (BroadcastReceiver) ve zamanlayıcı (AlarmScheduler).

## 💻 Kurulum ve Çalıştırma Adımları

Projeyi kendi bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyebilirsiniz:

**1. Projeyi İndirin:**
* Bu projeyi GitHub üzerinden `.zip` olarak indirin veya terminal üzerinden klonlayın:
  `git clone https://github.com/resul-uyanik/SubTrack.git`

**2. Android Studio'da Açın:**
* Android Studio'yu başlatın ve `Open` seçeneğine tıklayarak indirdiğiniz proje klasörünü seçin.
* Sağ alt köşede Gradle senkronizasyonunun (Sync) tamamlanmasını bekleyin.

**3. Cihaz Kurulumu:**
* **Fiziksel Telefon İçin:** Android telefonunuzda "Geliştirici Seçenekleri"ni açın ve "USB Hata Ayıklama"yı aktif edin. Telefonu bilgisayarınıza kabloyla bağlayın.
* **Emülatör İçin:** Android Studio'daki `Device Manager` üzerinden API 26 (Android 8.0) veya üzeri bir sanal cihaz oluşturun ve başlatın.

**4. Uygulamayı Çalıştırın:**
* Android Studio'nun üst menüsünde cihazınızın seçili olduğundan emin olun.
* Yeşil `Run` (Oynat) butonuna basın veya klavyeden `Shift + F10` tuşlarına basarak uygulamayı cihazınıza kurun.