import java.lang.NumberFormatException

interface IVehicle {
    fun countVehicles(){

    }
    fun distance()  {

    }
    fun absDistance()  {

    }
    fun gasStationVehiclesCount(){

    }
    fun vehiclesByActivityCount() {

    }
}

interface TrafficLights {
    fun greenLight() {
        println("Зеленый свет светофора")
    }
    fun redLight(){
        println("Красный свет светофора")
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
        else gasCost = 40.2
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


open class Car(type: String = "Автомобиль"): Vehicle(type), GasStation, TrafficLights, IVehicle
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


open abstract class Train(type: String = "Поезд"): Vehicle(type), TrafficLights, IVehicle
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


open class Bike(type: String = "Велосипед"): Vehicle(type), TrafficLights, IVehicle
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

    //this - обращается к busStation!!!!!
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
    val car3 = Car(110,333, "зеленый", "Kia", "Rio")

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

    val vehicles = setOf<Vehicle>(car1, car2, car3, truck1, truck2, bus1, bus2, bus3, trolleybus1, tram1,
        train1, train2, bike1, motorcycle1, motorcycle2, scooter1, moped1)
    for(vehicle in vehicles)
    {
        vehicle.startMovement()
    }
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


    //println("Выберите вариант дорожно-транспортной ситуации:")


    car1.goToGasStation()
    car1.refuel()
    car1.leaveGasStation()

    truck2.goToGasStation()
    motorcycle2.goToGasStation()
}