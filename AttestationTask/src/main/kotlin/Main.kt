import java.lang.NumberFormatException

interface TrafficLights {

    fun distance(drivingTime: Double, drivingSpeed: Int): Double {
        return (drivingSpeed / 3.6) * drivingTime
    }

    private fun startTrafficLights(carsArray: List<Car>, statisticsTime: Double): Int {
        var carsPassed = 0
        var carsSumDistance = 0.0
        var carsSpeed = 0.0
        var carAverageSpeed = 0.0
        var carAverageDistance = 0.0

        for (i in (carsArray.indices).random()..(carsArray.indices).random()) {
            carsArray[i].speed = (0..60).random()
            println(
                "${carsArray[i].type} движется со скоростью ${carsArray[i].speed} км/с, " +
                        "проехал ${distance(25.0, carsArray[i].speed)} метров"
            )

            carsSpeed += carsArray[i].speed
            carsSumDistance += distance(25.0, carsArray[i].speed)

            if (distance(25.0, carsArray[i].speed) >= 100) {
                carsPassed += 1
            }
        }

        carAverageSpeed = carsSpeed / carsArray.size
        carAverageDistance = carsSumDistance / carAverageSpeed

        val str = "\n Статистика светофора: \n" +
                "За $statisticsTime минут светофор проехало $carsPassed автомобилей \n" +
                "За $statisticsTime минут все машины проехали в сумме $carsSumDistance метров \n" +
                "Средняя скорость машины на светофоре составляет $carAverageSpeed м/с \n" +
                "За $statisticsTime минут автомобиль проезжает в среднем $carAverageDistance метров \n"
        return carsPassed
    }

    fun traffic(carsArray: List<Car>)
    {
        println("Вы на светофоре!")
        println("Один цикл состоит из зеленого (25 c) и красного света (55 с)")
        println("Автомобиль преодолел светофор, если он проехал >= 100 метров за время зеленго сигнала \n")

        print("Введите время сбора статистики в минутах: ")
        var inputTime = readln()!!
        var statisticsTime: Double = try {
            inputTime.toDouble()
        }
        catch (e: NumberFormatException){
            0.0
        }
        while(statisticsTime <= 0){
            println("Вы ввели неверное время! Поробуйте еще раз:")
            inputTime = readln()!!
            statisticsTime = try {
                inputTime.toDouble()
            }
            catch (e: NumberFormatException){
                0.0
            }
        }

        var statisticCycles = statisticsTime * 60
        var allCarsPassed = 0
        //var statistics: String = ""

        println("Запуск светофора на $statisticsTime минут:")
        while (statisticCycles > 0)
        {
            println("\n Зеленый сигнал светофора")
            allCarsPassed += startTrafficLights(carsArray, statisticsTime)
            //statistics = startTrafficLights(carsArray, statisticsTime)

            try {
                println("\n Красный сигнал светофора")
                Thread.sleep(5500)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            statisticCycles -= 100
        }

        //println(statistics)

        /**/
        println("\n Статистика светофора:")
        println("За $statisticsTime минут светофор проехало $allCarsPassed автомобилей")
        println("За $statisticsTime минут все машины проехали в сумме $ метров")
        println("Средняя скорость машины на светофоре составляет $ м/с")
        println("За $statisticsTime минут автомобиль проезжает в среднем $ метров")
    }
}

interface GasStation {
    fun goToGasStation()

    private fun payment(paymentMethod: Int, paymentCost: Double){
        if(paymentMethod == 1){
            println("Введите сумму денег:")
            var moneyInput = readln()!!
            var money: Double = try {
                moneyInput.toDouble()
            }
            catch (e: NumberFormatException){
                0.0
            }
            while(money < paymentCost) {
                println("Введеной суммы недостаточно!")
                println("Введите сумму денег:")
                money = readln().toDouble()
            }
            if(money >= paymentCost){
                println("Ваша сдача: ${money - paymentCost} \n")
            }
        }
        else {
            println("Оплата прошла успешно! \n")
        }
    }

    fun refuel() {
        println("Выберите номер колонки (1-3):")
        var stationNumberInput = readln()!!
        var stationNumber: Int
        stationNumber = try {
            stationNumberInput.toInt()
        } catch (e: NumberFormatException) {
            4
        }
        while (stationNumber <= 0 || stationNumber > 3){
            println("Введен неверный номер колонки! Пожалуйста, повторите ввод:")
            stationNumberInput = readln()!!
            stationNumber = try {
                stationNumberInput.toInt()
            } catch (e: NumberFormatException) {
                4
            }
        }

        println("Выберите номер топлива (92, 95):")
        var gasNumberInput = readln()!!
        var gasNumber: Int = try {
            gasNumberInput.toInt()
        } catch (e: NumberFormatException){
            1
        }
        while(gasNumber != 92 && gasNumber != 95){
            println("Введен неверный номер топлива! Пожалуйста, повторите ввод:")
            gasNumberInput = readln()!!
            gasNumber = try {
                gasNumberInput.toInt()
            } catch (e: NumberFormatException){
                1
            }
        }

        println("Введите количество литров:")
        var gasLitresInput = readln()!!
        var gasLitres: Double = try{
            gasLitresInput.toDouble()
        } catch (e: NumberFormatException){
           .0
        }
        while(gasLitres <= 0){
            println("Введено неверное количество топлива! Пожалуйста, повторите ввод:")
            gasLitresInput = readln()!!
            gasLitres = try{
                gasLitresInput.toDouble()
            } catch (e: NumberFormatException){
                .0
            }
        }

        var gasCost: Double
        if(gasNumber == 92)
            gasCost = 37.5
        else gasCost = 40.5
        var paymentCost = gasCost*gasLitres

        println("Ваши данные")
        println("Номер колонки: $stationNumber \n" +
                "Номер топлива: $gasNumber \n" +
                "Количество литров: $gasLitres \n" +
                "ИТОГО к оплате: $paymentCost")
        println("Выберите способ оплаты: 1 - наличные, 2 - банковская карта")
        var paymentMethod = readln().toInt()
        payment(paymentMethod, paymentCost)
    }

    fun leaveGasStation()
}

interface RoadCafe {
    fun goToRoadCafe()

    private fun payment(paymentMethod: Int, paymentCost: Double){
        if(paymentMethod == 1){
            println("Введите сумму денег:")
            var moneyInput = readln()!!
            var money: Double = try {
                moneyInput.toDouble()
            }
            catch (e: NumberFormatException){
                0.0
            }
            while(money < paymentCost) {
                println("Введеной суммы недостаточно!")
                println("Введите сумму денег:")
                money = readln().toDouble()
            }
            if(money >= paymentCost){
                println("Ваша сдача: ${money - paymentCost} \n")
            }
        }
        else {
            println("Оплата прошла успешно! \n")
        }
    }

    fun buyFood() {
        println("Выберите номер товара")
        println("1 - Булочка")
        println("2 - Пицца")
        println("3 - Почик")

        var inputItemNumber = readln()!!
        var itemNumber: Int
        itemNumber = try {
            inputItemNumber.toInt()
        } catch (e: NumberFormatException) {
            4
        }
        while (itemNumber <= 0 || itemNumber > 3){
            println("Введен неверный номер товара! Пожалуйста, повторите ввод:")
            inputItemNumber = readln()!!
            itemNumber = try {
                inputItemNumber.toInt()
            } catch (e: NumberFormatException) {
                4
            }
        }

        println("Введите количество:")
        var itemAmountInput = readln()!!
        var itemAmount: Double = try{
            itemAmountInput.toDouble()
        } catch (e: NumberFormatException){
            .0
        }
        while(itemAmount <= 0){
            println("Введено неверное количество! Пожалуйста, повторите ввод:")
            itemAmountInput = readln()!!
            itemAmount = try{
                itemAmountInput.toDouble()
            } catch (e: NumberFormatException){
                .0
            }
        }

        var itemCost: Double
        var itemName: String
        when (itemNumber) {
            1 -> {
                itemCost = 30.0
                itemName = "Булочка"
            }
            2 -> {
                itemCost = 85.0
                itemName = "Пицца"
            }
            else -> {
                itemCost = 60.0
                itemName = "Пончик"
            }
        }
        var paymentCost = itemCost*itemAmount

        println("Ваши данные")
        println("Товар: $itemName \n" +
                "Количество: $itemAmount \n" +
                "ИТОГО к оплате: $paymentCost")
        println("Выберите способ оплаты: 1 - наличные, 2 - банковская карта")
        var paymentMethod = readln().toInt()
        payment(paymentMethod, paymentCost)
    }

    fun leaveRoadCafe()
}

open abstract class Vehicle(open var type: String)
{
    abstract var tires: String
    abstract var roadSurface: String
    abstract var terrain: String
    abstract var yearSeason: String
    abstract var speed: Int
    abstract var serialNumber: Int
    abstract var mooving: Boolean

    open fun startMovement() {
        this.mooving = true
        println("${this.type} начал движение")
    }
    open fun stopMovement(){
        this.mooving = false
        println("${this.type} остановился")
    }
    open fun isMoving(){
        if(this.mooving)
            println("${this.type} в пути")
        else println("${this.type} на парковке")
    }
    open fun info(): String = "${this.type} \n " +
            "${this.tires} \n " +
            "${this.roadSurface} \n " +
            "${this.terrain} \n " +
            "${this.yearSeason} \n " +
            "Скорость - ${this.speed} км/ч \n" +
            "Номер - ${this.serialNumber} \n" +
            "Находится в движении: ${this.mooving}"
}


open class Car(type: String = "Автомобиль"): Vehicle(type), GasStation, TrafficLights, RoadCafe, IVehicle
{
    override var tires = "Автомобильные шины"
    override var roadSurface = "Любое дорожное покрытие"
    override var terrain = "Любой рельеф"
    override var yearSeason = "Всесезонный"
    override var speed: Int = 60
    override var serialNumber: Int = 0
    override var mooving: Boolean = false
    var color: String = ""
    var brand: String = ""
    var model: String = ""


    constructor(speed: Int, serialNumber: Int) : this() {
        this.speed = speed
        this.serialNumber = serialNumber
        println("An instance of the Car class was successfully created.")
    }

    constructor(speed: Int, serialNumber: Int, color: String, brand: String, model: String) : this() {
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        println("An instance of the Car class was successfully created.")
    }

    override fun info(): String = "${this.type} \n" +
            "${this.tires} \n " +
            "${this.roadSurface} \n " +
            "${this.terrain} \n " +
            "${this.yearSeason} \n " +
            "Скорость - ${this.speed} км/ч \n" +
            "Номер - ${this.serialNumber} \n" +
            "Цвет - ${this.color} \n" +
            "Бренд - ${this.brand} \n" +
            "Модель - ${this.model} \n" +
            "Находится в движении: ${this.mooving}"

    override fun goToGasStation() {
        println("${this.type} с номером ${this.serialNumber} прибыл на автозаправку \n")
    }

    override fun leaveGasStation() {
        println("${this.type} с номером ${this.serialNumber} покинул автозаправку \n")
    }

    override fun goToRoadCafe() {
        println("${this.type} с номером ${this.serialNumber} остановился в дорожном кафе  \n")
    }

    override fun leaveRoadCafe() {
        println("${this.type} с номером ${this.serialNumber} покинул дорожное кафе  \n")
    }
}

open class Truck(type: String = "Грузовой автомобиль"): Car(type)
{
    var cargoWeight: Double = 0.0

    constructor(speed: Int, serialNumber: Int, color: String, brand: String, model: String, cargoWeight: Double): this()
    {
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        this.cargoWeight = cargoWeight
        println("An instance of the Truck class was successfully created.")
    }

    constructor(type: String, speed: Int, serialNumber: Int, color: String, brand: String, model: String, cargoWeight: Double) : this() {
        this.type = type
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        this.cargoWeight = cargoWeight
        println("An instance of the Truck class was successfully created.")
    }

    override fun info(): String = "${this.type} \n" +
            "${this.tires} \n " +
            "${this.roadSurface} \n " +
            "${this.terrain} \n " +
            "${this.yearSeason} \n " +
            "Скорость - ${this.speed} км/ч \n" +
            "Номер - ${this.serialNumber} \n" +
            "Цвет - ${this.color} \n" +
            "Бренд - ${this.brand} \n" +
            "Модель - ${this.model} \n" +
            "Вес груза - ${this.cargoWeight} \n" +
            "Находится в движении: ${this.mooving}"
}

open class Bus(type: String = "Автобус"): Car(type)
{
    override var roadSurface = "Асфальт"
    override var terrain = "Гладкое дорожное покрытие"
    override var speed: Int = 60
    var passengerSeats: Int = 0

    constructor(speed: Int, serialNumber: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        println("An instance of the Bus class was successfully created.")
    }

    constructor(speed: Int, serialNumber: Int, color: String, brand: String, model: String, passengerSeats: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        this.passengerSeats = passengerSeats
        println("An instance of the Bus class was successfully created.")
    }
}

open class Trolleybus(type: String = "Троллейбус"): Bus(type) //DOES NOT IMPLEMENT THE INTERFACE GasStation!!
{
    val feature = "Работает на электричестве"

    constructor(speed: Int, serialNumber: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        println("An instance of the Trolleybus class was successfully created.")
    }

    constructor(speed: Int, serialNumber: Int, color: String, brand: String, model: String, passengerSeats: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        this.passengerSeats = passengerSeats
        println("An instance of the Trolleybus class was successfully created.")
    }
}


open abstract class Train(type: String = "Поезд"): Vehicle(type), IVehicle
{
    override var tires = "Колесная пара"
    override var roadSurface = "Железнодорожные рельсы"
    override var terrain = "Железнодорожное покрытие"
    override var yearSeason = "Всесезонный"
    override var speed: Int = 100
    override var serialNumber: Int = 0
    override var mooving: Boolean = false
}

open class PassengerTrain(type: String = "Пассажирский поезд"): Train(type)
{
    var passengerSeats: Int = 0

    constructor(speed: Int, serialNumber: Int, passengerSeats: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        this.passengerSeats = passengerSeats
        println("An instance of the PassengerTrain class was successfully created.")
    }
}

open class CargoTrain(type: String = "Грузовой поезд"): Train(type)
{
    var cargoWeight: Double = 0.0

    constructor(speed: Int, serialNumber: Int, cargoWeight: Double): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        this.cargoWeight = cargoWeight
        println("An instance of the CargoTrain class was successfully created.")
    }
}

open class Tram(type: String = "Трамвай"): Train(type)
{
    val feature = "Работает на электричестве"

    constructor(speed: Int, serialNumber: Int): this() {
        this.speed = speed
        this.serialNumber = serialNumber
        println("An instance of the Tram class was successfully created.")
    }
}


open class Bike(type: String = "Велосипед"): Vehicle(type), RoadCafe, IVehicle
{
    override var tires = "Велосипедные шины"
    override var roadSurface = "Асфальт"
    override var terrain = "Гладкий рельеф"
    override var yearSeason = "Теплое время года"
    override var speed: Int = 15
    override var serialNumber: Int = 0
    override var mooving: Boolean = false

    constructor(speed: Int, serialNumber: Int): this(){
        this.speed = speed
        this.serialNumber = serialNumber
        println("An instance of the Bike class was successfully created.")
    }

    override fun goToRoadCafe() {
        println("${this.type} с номером ${this.serialNumber} остановился в дорожном кафе  \n")
    }

    override fun leaveRoadCafe() {
        println("${this.type} с номером ${this.serialNumber} покинул дорожное кафе  \n")
    }
}

open class Motorcycle(): Bike(), GasStation
{
    override var type: String = "Мотоцикл"
    override var tires = "Шины для мотоцикла"
    override var yearSeason = "Всесезонный"
    override var speed: Int = 70
    var color: String = ""
    var brand: String = ""
    var model: String = ""

    constructor(speed: Int, serialNumber: Int, color: String, brand: String, model: String): this(){
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        println("An instance of the Motorcycle class was successfully created.")
    }

    constructor(type: String, speed: Int, serialNumber: Int, color: String, brand: String, model: String): this(){
        this.type = type
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        println("An instance of the Motorcycle class was successfully created.")
    }

    constructor(type: String, tires: String, yearSeason: String, speed: Int, serialNumber: Int, color: String, brand: String, model: String): this(){
        this.type = type
        this.tires = tires
        this.yearSeason = yearSeason
        this.speed = speed
        this.serialNumber = serialNumber
        this.color = color
        this.brand = brand
        this.model = model
        println("An instance of the Motorcycle class was successfully created.")
    }

    override fun info(): String = "${this.type} \n" +
            "${this.tires} " +
            "${this.roadSurface} \n" +
            "${this.terrain} \n" +
            "${this.yearSeason} \n " +
            "Скорость - ${this.speed} км/ч \n" +
            "Номер - ${this.serialNumber} \n" +
            "Цвет - ${this.color} \n" +
            "Бренд - ${this.brand} \n" +
            "Модель - ${this.model} \n" +
            "Находится в движении: ${this.mooving}"

    override fun goToGasStation() {
        println("${this.type} с номером ${this.serialNumber} прибыл на автозаправку")
    }

    override fun leaveGasStation() {
        println("${this.type} с номером ${this.serialNumber} покинул автозаправку \n")
    }
}


class Station<T>() //station for buses or trains
{
    var timeOfStart: Double = 0.0
    var timeOfStop: Double = 0.0
    private var isOnStation: Boolean = false
    private var vehiclesCount: Int = 0

    constructor(t: T) : this() {
        println("$t был добавлен на станцию \n")
        this.isOnStation = true
        vehiclesCount++
    }

    fun addVehicle(t: T) {
        println("$t был добавлен на станцию \n")
        this.isOnStation = true
        vehiclesCount++
    }

    fun leaveStation(t: T, timeOfStart: Double) {
        if(this.isOnStation)
        {
            vehiclesCount--
            this.isOnStation = false
            println("$t уехал со станции в $timeOfStart \n")
        }
        else if(vehiclesCount == 0) {
            println("Станция пуста! \n")
        }
        else {
            println("$t уже уехал со станции! \n")
        }
    }

    fun returnToTheStation(t: T, timeOfStop: Double) {
        if(this.isOnStation) {
            println("$t уже на станции! \n")
        }
        else {
            vehiclesCount++
            this.isOnStation = true
            println("$t вернулся на станцию в $timeOfStop \n")
        }
    }

    fun info() {
        println("На станции находится $vehiclesCount автомобильных средства \n")
    }
}

fun main() {

    val car1 = Car(110,111, "красный", "Mercedes-Benz", "AMG GT")
    val car2 = Car(90, 222, "белый", "Nissan", "Quashqai")
    val car3 = Car(105,333, "зеленый", "Honda", "Civic")
    val car4 = Car(70,458, "желтый", "Kia", "Rio")
    val car5 = Car(60,267, "черный", "Lada", "Granta")

    val truck1 = Truck(70, 1547, "оранжевый", "SHACMAN", "6Х4 X3000", 3.2)
    val truck2 = Truck("Автомобиль-манипулятор", 55, 4587, "белый", "Nissan", "Diesel UD", 10.0)

    val bus1 = Bus(70, 1010, "зеленый", "ЛиАЗ", "5256", 55)
    val bus2 = Bus(65, 2012, "белый", "МАЗ", "206", 80)
    val bus3 = Bus(60, 4251, "белый", "НефАЗ", "5299", 101)
    val trolleybus1 = Trolleybus(60, 3013, "желтый", "ПКТС", "6281", 62)
    val tram1 = Tram(50, 1245)

    val train1 = PassengerTrain(150, 5175, 250)
    val train2 = CargoTrain(100, 4812, 3000.5)

    val bike1 = Bike(15, 1001)
    val motorcycle1 = Motorcycle(80, 1211, "черный", "Racer", "Storm RC250XZR-A")
    val motorcycle2 = Motorcycle(90, 1222, "оранжевый", "Racer", "Storm RC250XZR-A")
    val scooter1 = Motorcycle("Скутер", "Шины для скутера", "Теплое время года", 70, 1110, "красный","Racer", "RC150T-15 STELLS SPORT")
    val moped1 = Motorcycle("Мопед", "Шины для скутера", "Теплое время года",60, 1303, "желтый", "Racer", "RC50QT-6F Flame")

    val vehicles = setOf<Vehicle>(car1, car2, car3, car4, car5, truck1, truck2, bus1, bus2, bus3, trolleybus1, tram1,
        train1, train2, bike1, motorcycle1, motorcycle2, scooter1, moped1)
    for(vehicle in vehicles) { vehicle.info() }
    println()

    for(vehicle in vehicles) { vehicle.startMovement() }
    println()

    /*
    val busStation: Station<Bus> = Station<Bus>(bus1)
    busStation.addVehicle(bus2)
    busStation.addVehicle(bus3)

    busStation.leaveStation(bus2, 6.48)
    busStation.leaveStation(bus1,9.05)
    busStation.leaveStation(bus3, 10.10)

    busStation.returnToTheStation(bus2, 15.15)
    busStation.returnToTheStation(bus3, 20.05)
    busStation.info()*/



    println("Выберите вариант дорожно-транспортной ситуации:")
    println("1 - Светофор")
    println("2 - Автозаправка")
    println("3 - Дорожное кафе")

    var input = readln()!!
    var option: Int
    option = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        4
    }
    while (option <= 0 || option > 3){
        println("Введен неверный номер! Пожалуйста, повторите ввод:")
        input = readln()!!
        option = try {
            input.toInt()
        } catch (e: NumberFormatException) {
            4
        }
    }

    when (option) {
        1 -> {
            val carsArray = arrayListOf<Car>(car1, car2, car3, car4, car5, truck1, truck2, bus1, bus2, bus3)
            car1.traffic(carsArray)
            println()
        }
        2 -> {
            car1.goToGasStation()
            car1.refuel()
            println("Хотите покинуть автозаправку? (1/0)")
            var inputGasStation = readln()!!
            var optionGasStation: Int
            optionGasStation = try {
                inputGasStation.toInt()
            } catch (e: NumberFormatException) {
                2
            }
            while (optionGasStation != 0 && optionGasStation != 1){
                println("Введен неверный номер! Пожалуйста, повторите ввод:")
                inputGasStation = readln()!!
                optionGasStation = try {
                    inputGasStation.toInt()
                } catch (e: NumberFormatException) {
                    2
                }
            }
            if(optionGasStation == 1)
                car1.leaveGasStation()
            println()
        }
        3 -> {
            motorcycle2.goToRoadCafe()
            motorcycle2.buyFood()
            println("Хотите покинуть дорожное кафе? (1/0)")
            var inputRoadCafe = readln()!!
            var optionRoadCafe: Int
            optionRoadCafe = try {
                inputRoadCafe.toInt()
            } catch (e: NumberFormatException) {
                2
            }
            while (optionRoadCafe != 0 && optionRoadCafe != 1){
                println("Введен неверный номер! Пожалуйста, повторите ввод:")
                inputRoadCafe = readln()!!
                optionRoadCafe = try {
                    inputRoadCafe.toInt()
                } catch (e: NumberFormatException) {
                    2
                }
            }
            if(optionRoadCafe == 1)
                car1.leaveGasStation()
            println()
        }
    }

    for(vehicle in vehicles) { vehicle.stopMovement() }
    println()

}
