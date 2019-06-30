1. AC1

given: parking lot has enough space

when: a car want into parking lot 

then: return the car a parking ticket 

2. AC2

given: parking lot space is full

when: a car want into parking lot

then: reject the car  

3. AC3

given: a valid ticket

when: parking lot get the ticket

then: return the corresponding car 

4. AC4

given: a invalid ticket

when: parking lot get the ticket

then: reject the ticket

5. AC5

given: a valid ticket

when: parking lot get the ticket twice

then: reject the second ticket

---

6. AC6

given: Parking Boy有两个停车场，第一个停车场A和第二个停车场B，A和B都有空位

when: Parking Boy停车

then: 返回一张票给车，车停到第一个停车场A

7. AC7

given: Parking Boy有两个停车场，有第一个停车场A和第二个停车场B，A没空位，B有空位

when: Parking Boy停车

then: 返回一张票给车，车停到第二个停车场B

8. AC8

given: Parking Boy有两个停车场，有第一个停车场A和第二个停车场B，A有空位，B没空位

when: Parking Boy停车

then: 返回一张票给车，车停到第一个停车场A

9. AC9

given: Parking Boy有两个停车场，有第一个停车场A和第二个停车场B，A和B都没有空位

when: Parking Boy停车

then: 拒绝停车

10. AC10

given: 有效的票

when: Parking Boy取车

then: 取到对应的车

11. AC11

given: 无效的票

when: 取车

then: 拒绝取车

12. AC12

given: Parking Boy有两个停车场，有第一个停车场A和第二个停车场B，车停在B停车场并且用对应的票

when: 取车

then: 取到对应的车

---

13. AC13

given: Parking boy有两个停车场A和B，有第一个停车场A和第二个停车场B，并且B的空位比A的空位多

when: 停车

then: 返回一张票给车，车停到第一个停车场B

14. AC14

given: 有效的票

when: Parking Boy取车

then: 取到对应的车

15. AC15

given: 无效的票

when: 取车

then: 拒绝取车

16. AC16

given: Parking Boy有两个停车场，有第一个停车场A和第二个停车场B，车停在B停车场并且用对应的票

when: 取车

then: 取到对应的车

---

17. AC17

given: Parking manager有一个Graduate Parking Boy管理着停车场A并且有空位和一个Smart Parking Boy管理着停车场B和一个单独的停车场C

when: 停车

then: 车通过Graduate Parking Boy停到了停车场A并且返回了一张票给车

18. AC18

given: Parking manager有一个Graduate Parking Boy管理着停车场A并且没有空位和一个Smart Parking Boy管理着停车场B有空位和一个单独的停车场C

when: 停车

then: 车通过Smart Parking Boy停到了停车场B并且返回了一张票给车

19. AC19

given: Parking manager有一个Graduate Parking Boy管理着停车场A并且没有空位和一个Smart Parking Boy管理着停车场B没有空位和一个单独的停车场C有空位

when: 停车

then: 车让车主自己停到了停车场C并且返回了一张票给车
