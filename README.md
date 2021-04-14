# Koşum detayı
Testlerin koşulabilmesi işi RunTestScript.sh dosyası çalıştırılmalıdır. 
Koşulacak makinede minimum olarak docker bulunmalıdır.

# Dosya içeriği
Docker-compose.yaml dosyasında selenium grid ve Dockerfile birlikte çalışıyor. 
Crossbrowser için iki ayrı image build alıyorum.
Testler selenium grid içerisinde koşuyor.

#Notlar:
1- Chapter-1 içindeki 2.madde için network loglarını(Image filtresi ve response filtresi ile) dosyaya kaydediyorum ancak datalar farklı farklı geldiği için modelleyip istenilen dataları ayrıştıramadım.
2- Chapter-2 için gerekli kontrolleri yazdım ancak bir servise ve datasource tarafına bağlanabilecek şekilde(geliştirmeye açık) bıraktım. Config tarafına url eklenmesi yeterli olacak ama datasource seçimine göre repo queryler farklı olacağından eklemedim.