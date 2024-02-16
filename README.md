Tizimga phone yoki email orqali kirish mumkin
Default 1 ta super admin boladi va u hamma ishini qilaoladi
Rolelar yaratishi va tizimdagi userga role berishi mumkin
Role yaratganda permissionlarga roleni CRUD qilish va user ni CRUD qilish kabi
permissionlarni beraolmaydi. Misol uchun 
	Role: Manager
	Permissions: ADD_USER, DELETE_USER, ..., ADD_ROLE, GET_ROLE...
Faqat BLOCK_USER, UNBLOCK_USER deagn ikkita permissondan va boshqalardan foydalanadi.

* Authentication
Tizmga email yoki phone orqali ro'yxatdan o'tish mumkin
Telefon raqamini biriktirishi, telefon raqamini o'zgartirishi(sms code orqali),
emailini o'zgartirishi(code orqali) mumkin.



Objectlar

- USER
ism familiya, parol, tel, email, activeligi, glavniy rasmi boladi

- Mutaxasislik yo'nalishlari
nomi, rasmi, tafsiloti

- Mutaxasislik yo'nalishidagi modullar
nomi, haqida

- Modullarning mavzulari
nomi, 

- Savollar (qaysidir darsga qarashli savollar)
text,
savolga kamida uchta javob boladi, bittasi to'gri boladi

- Quiz
Harkim qaysidir MAVZU larni tanlab savollar yechisni boshlaydi
random tarizda osha mavzulardan 15 ta savol beriladi.
Savollar javob topish soniyasiga qarab 3 xil boladi
  easy(1 ball, 20 soniya), medium(2 ball, 15 soniya), hard(3 ball, 10 soniya)

nechta to'g'ri va nechta xato ishlaganligi haqida malumot korsariladi

Qaysi mutaxasislik ballariga qarab ballarga qarab userlar orasidan eng kop balligini topish mumkin bolsin va harkim o'zini o'rnini bila olsin



 

