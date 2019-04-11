Wongnai Backend Assignment
===

โจทย์ที่เราจะมาทำกันก็คือ ทำระบบ search ภาพยนตร์ โดยต้องทำเป็น web service และคืนผลลัพธ์เป็น JSON ครับ

Project ได้ config dependencies ที่จำเป็น รวมถึง Controller, Model และ Database ก็ได้ implement + config ไว้เรียบร้อยแล้ว
นอกจากนั้น project ตัวนี้มี integration test ให้ครบหมดทุกส่วน โดยมี test case เฉพาะที่จำเป็นต้องผ่านอยู่ครบถ้วน

Requirement ขั้นพื้นฐาน

```
Java 8 (JDK 1.8) จะเป็น OracleJDK หรือ OpenJDK ก็ได้
Maven 3.5 ขึ้นไป
IDE แนะนำให้ใช้ IntelliJ 2018.3+ Community Edition
Internet ควรทำงานได้เป็นอย่างดี มีความเร็วขั้นต่ำ 10 Mbps
ใน Command Prompt/Terminal ควรจะต้องใช้คำสั่ง mvn และ java ได้
```

เราสามารถ start server ขึ้นมาทดสอบได้เลย เริ่มจากเปิด terminal
แล้วเข้าไปยัง directory ของ project นี้ จากนั้นพิมพ์คำสั่ง

```
mvn spring-boot:run
```

จากนั้นทดสอบเข้าไปที่ URL http://localhost:8080/movies ควรจะได้ Hello World!

จะขอไม่อธิบายการทำงานของ Spring Boot, JPA (Hibernate) และอื่นๆ นะครับ
ถ้าไม่เข้าใจ Framework เหล่านี้ให้ศึกษาเพิ่มเติมด้วยตัวเองครับ

## การส่งงาน
เนื่องจากว่าเราได้ส่ง project นี้ให้ทาง email อยู่ในรูปแบบไฟล์ zip ขอให้คุณ extract zip ออกมา
จากนั้น setup git ให้เรียบร้อยแล้ว push ขึ้น repository ของคุณเองใน GitHub จากนั้น
reply email กลับมาหาเราพร้อม link ของ project GitHub ของคุณเมื่อทำโจทย์เรียบร้อยหมดแล้ว

อย่าลืมว่าต้องเปิด repository ใน GitHub ให้เป็น public ด้วย

โจทย์ในแต่ละข้อที่ได้ทำไป ขอให้ commit ขั้นตอนต่างๆ ให้เป็นระเบียบเรียบร้อย เพราะเราจะดูในส่วนนี้ด้วย


## โจทย์

มีทั้งหมด 4 ข้อ ให้ทำตามลำดับห้ามข้ามขั้นตอนเด็ดขาด ไม่เช่นนั้นอาจจะทำไม่สำเร็จได้

ในระหว่างทำโจทย์ อาจมีคำถามเกิดขึ้นหรืออาจพบ case ต่างๆ ที่ไม่ได้ระบุเอาไว้ในโจทย์ ให้คุณตัดสินใจด้วยตัวเอง
ได้เลยว่าจะ handle อย่างไร โดยจะต้องคำนึงถึงคุณลักษณะของระบบที่ดีไว้ด้วยเสมอ

เราอนุญาตให้แก้ไข code อย่างไรก็ได้ตราบใดที่ test ทั้งหมดของที่ได้เตรียมไว้ให้ยังทำงานได้ปกติ
และคุณสามารถเพิ่ม test case เองได้ตามความเหมาะสม

อย่างไรก็ตาม การแก้ไข test case ที่เราได้เตรียมไว้นั้นเป็นสิ่งที่ทำได้โดยจะต้องไม่กระทบกับ behavior ภายนอกของระบบ
และ test case ของเราได้เตรียมไว้เฉพาะกรณีที่เป็น happy case เท่านั้น

### ข้อที่ 1

เหตุการณ์: เราจะทำระบบค้นหาภาพยนตร์จากชื่อ โดยเราไปเจอว่ามีฐานข้อมูลชื่อภาพยนตร์อยู่ใน internet และไฟล์อยู่ใน format JSON เรียบร้อยแล้ว
ในขั้นแรก เราจะต้องหาทางเขียนโปรแกรมให้ไป download ไฟล์ JSON และ convert ให้กลายเป็น object ใน Java ที่ถูกต้องให้ได้ก่อน

ฐานข้อมูลที่เราเจอนั้น อาจมีการอัพเดทบางครั้งคราว ดังนั้นเราไม่สามารถ download ลงมาไว้ในเครื่องของเราเองได้ ต้องดึงผ่าน URL เท่านั้น

ให้เปิดไฟล์ `MovieDataServiceImpl.java` จากนั้น implement method `fetchAll` ให้เรียบร้อย
โดยจะต้อง download ข้อมูลจาก URL ที่กำหนดไว้ในตัวแปร `MOVIE_DATA_URL` แล้ว convert ให้เป็น object `MoviesResponse`

ย้ำอีกครั้งว่า ห้าม save file ลงไว้ในเครื่องเด็ดขาดเพราะฐานข้อมูลอาจมีการอัพเดทได้

ระหว่างที่ implement สามารถ run test ได้ที่ class `MovieDataServiceImplIntegrationTest`
และขอให้ใช้ IntelliJ ในการ run test เฉพาะ class นี้ไปก่อน เมื่อทุก test case ผ่านแล้วสามารถทำข้อต่อไปได้


### ข้อที่ 2
เหตุการณ์: เราสามารถ download ฐานข้อมูลและ convert ให้เป็น object ที่เหมาะสมได้แล้ว เราจึงลองเริ่มทำ web service อย่างง่ายดูก่อน
เพื่อจะได้เอาไปเก็บ feedback จากทีม โดยระบบจะต้องรองรับการค้นหาจากชื่อของภาพยนตร์บางส่วนได้

ให้ไปที่ class `SimpleMovieSearchService` จากนั้น implement ระบบค้นหาจากชื่อภาพยนตร์ให้เรียบร้อย
โดยใช้ข้อมูลจาก `MovieDataService` ที่เราได้ทำในข้อที่ 1 ไปเรียบร้อยแล้ว

ตัวอย่างการค้นหา:

สมมติว่าผู้ใช้พิมพ์คำว่า `Glorious` ระบบจะค้นหาว่ามีภาพยนตร์ใดบ้างที่มีคำนี้ปรากฎอยู่ จากฐานข้อมูลจะเจอ 7 เรื่อง ได้แก่

```
The Glorious Lady
The Glorious Fool
One Glorious Day
One Glorious Night
Glorious Betsy
His Glorious Night
Borat! Cultural Learnings of America for Make Benefit Glorious Nation of Kazakhstan
```

อย่างไรก็ตามในขั้นตอนนี้เราจะไม่รองรับการค้นหาแบบไม่เต็มคำ เช่น `Glorio` จะต้องไม่เจอภาพยนตร์ใดๆ

เมื่อผ่านแล้วให้ start server แล้วลองทำการค้นหาดูอีกครั้งว่าระบบยังทำงานได้ปกติหรือไม่
โดยเข้า URL http://localhost:8080/movies/search?q=Glorious
รวมถึงทดสอบ run test `SimpleMovieSearchServiceIntegrationTest` และ `MoviesControllerIntegrationTest` ให้ผ่านด้วย

### ข้อที่ 3
เหตุการณ์: ทีมได้บอกกับเราว่า ระบบของเราทำงานช้าเกิดไป เราจึงได้ไปตรวจสอบแล้วพบว่า ทุกครั้งที่มีการค้นหา
ระบบจะไป download ข้อมูลเสมอ ซึ่งข้อมูลก็มีขนาดใหญ่พอสมควร

เราจึงคิดว่าระบบจะต้อง download ข้อมูลแค่เพียงครั้งเดียวตอน start server เพื่อเอาไปเก็บไว้ใน database ชั่วคราว
เพื่อให้การค้นหาทำได้เร็ว ไม่ต้อง download ทุกครั้งเหมือนในข้อ 2

อย่างไรก็ตาม database ของเราไม่รองรับการค้นหาเป็นคำๆ เหมือนในข้อ 2 เราจึงต้องรองรับการค้นหาบางส่วนของคำด้วย

ไฟล์ที่เกี่ยวข้องในข้อดีคือ
1. DatabaseMovieSearchService
2. MovieDataSynchronizer
3. Movie
4. MovieRepository

ขอให้ทำอย่างไรก็ได้ให้ test ในไฟล์ `DatabaseMovieSearchServiceIntegrationTest` ผ่านทุกกรณี

เมื่อผ่านแล้ว ให้แก้ไข bean `simpleMovieSearchService` เป็น `databaseMovieSearchService` ในไฟล์ `MoviesController`
จากนั้นให้ทดสอบ start server แล้วลองทำการค้นหาดูอีกครั้งว่าระบบยังทำงานได้ปกติหรือไม่
รวมถึงทดสอบ run test `MoviesControllerIntegrationTest` ให้ผ่านด้วย


### ข้อที่ 4
เหตุการณ์: ทีมของเราอยากให้ระบบกลับมาค้นหาเป็นคำๆ ได้เหมือนเดิม และยิ่งไปกว่านั้น เราพบว่าการค้นหาผ่าน database
ในแบบที่เราทำในข้อ 3 นั้น ใช้เวลาเป็น O(N*M) โดย N คือจำนวนภาพยนตร์ในฐานข้อมูล และ M คือความยาวของชื่อภาพยนตร์

เราจึงทดสอบสมมติฐานนี้โดยการยิง load testing เข้าไป พบว่า CPU ทำงานหนัก เราอยากให้ระบบค้นหาทำงานได้รวดเร็ว
และลดการใช้ CPU ลงเพื่อรองรับ load ที่มากขึ้น

เราได้ศึกษาการทำ Inverted Index เมื่อไม่นานมานี้ และเราคิดว่าวิธีนี้จะช่วยลดเวลาที่ใช้ลงได้
และยังทำให้เราได้ความสามารถในการค้นหาเป็นคำๆ กลับมาอีกด้วย

ขอให้ implement `InvertedIndexMovieSearchService` ให้เรียบร้อย โดยเรามีคำอธิบายวิธีทำ inverted index ให้แล้ว

คุณไม่จำเป็นต้องทำวิธียาก เราขอให้ทำ inverted index แบบ in-memory ด้วย data structure ที่ Java มีอยู่ก็สามารถทำสำเร็จได้
ในส่วนของ BigO ที่ได้ขึ้นอยู่กับวิธีที่คุณเลือกใช้ อย่างไรก็ตาม หากคุณต้องการทำท่ายาก เราก็ไม่อาจบังคับคุณได้

เมื่อ implement เสร็จให้รัน test ในไฟล์ `InvertedIndexMovieSearchServiceIntegrationTest` และทุก case ต้องผ่าน

เมื่อผ่านแล้ว ให้แก้ไข bean `simpleMovieSearchService` เป็น `invertedIndexMovieSearchService` ในไฟล์ `MoviesController`
จากนั้นให้ทดสอบ start server แล้วลองทำการค้นหาดูอีกครั้งว่าระบบยังทำงานได้ปกติหรือไม่
รวมถึงทดสอบ run test `MoviesControllerIntegrationTest` ให้ผ่านด้วย


### ขั้นตอนสุดท้ายก่อนส่งงาน
ทดสอบ run test ทั้งหมด โดยเปิด terminal แล้วพิมพ์คำสั่ง

```
mvn clean install
```

ควรจะ success ทั้งหมด

จากนั้น start server ขึ้นมาอีกครั้ง พร้อมทดสอบใช้งานด้วยตัวเอง

ถ้าทุกอย่างเรียบร้อยดี สามารถส่งงานให้เราได้เลย
