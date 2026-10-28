// Adaptación del codelab de Android Developers: clases y objetos en Kotlin.
// https://developer.android.com/codelabs/basic-android-kotlin-compose-classes-and-objects
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class RangeRegulator(initialValue: Int, private val min: Int, private val max: Int) : ReadWriteProperty<Any?, Int> {
    private var fieldData = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int = fieldData
    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in min..max) fieldData = value
    }
}

open class SmartDevice(val name: String, val category: String) {
    var deviceStatus = "off"
        protected set
    open val deviceType = "unknown"
    open fun turnOn() { deviceStatus = "on" }
    open fun turnOff() { deviceStatus = "off" }
    fun printDeviceInfo() = println("Device name: $name, category: $category, type: $deviceType")
}

class SmartTvDevice(name: String, category: String) : SmartDevice(name, category) {
    override val deviceType = "Smart TV"
    var speakerVolume by RangeRegulator(2, 0, 100)
        private set
    var channelNumber by RangeRegulator(1, 0, 200)
        private set
    override fun turnOn() { super.turnOn(); println("$name encendido") }
    override fun turnOff() { super.turnOff(); println("$name apagado") }
    fun increaseSpeakerVolume() { speakerVolume++; println("Volumen: $speakerVolume") }
    fun decreaseVolume() { speakerVolume--; println("Volumen: $speakerVolume") }
    fun nextChannel() { channelNumber++; println("Canal: $channelNumber") }
    fun previousChannel() { channelNumber--; println("Canal: $channelNumber") }
}

class SmartLightDevice(name: String, category: String) : SmartDevice(name, category) {
    override val deviceType = "Smart Light"
    var brightnessLevel by RangeRegulator(0, 0, 100)
        private set
    override fun turnOn() { super.turnOn(); brightnessLevel = 2; println("$name encendida") }
    override fun turnOff() { super.turnOff(); brightnessLevel = 0; println("$name apagada") }
    fun increaseBrightness() { brightnessLevel++; println("Brillo: $brightnessLevel") }
    fun decreaseBrightness() { brightnessLevel--; println("Brillo: $brightnessLevel") }
}

class SmartHome(val smartTvDevice: SmartTvDevice, val smartLightDevice: SmartLightDevice) {
    val deviceTurnOnCount: Int get() = listOf(smartTvDevice, smartLightDevice).count { it.deviceStatus == "on" }
    fun turnOnTv() { if (smartTvDevice.deviceStatus != "on") smartTvDevice.turnOn() }
    fun turnOffTv() { if (smartTvDevice.deviceStatus == "on") smartTvDevice.turnOff() }
    fun turnOnLight() { if (smartLightDevice.deviceStatus != "on") smartLightDevice.turnOn() }
    fun turnOffLight() { if (smartLightDevice.deviceStatus == "on") smartLightDevice.turnOff() }
    fun increaseTvVolume() { if (smartTvDevice.deviceStatus == "on") smartTvDevice.increaseSpeakerVolume() }
    fun decreaseTvVolume() { if (smartTvDevice.deviceStatus == "on") smartTvDevice.decreaseVolume() }
    fun changeTvChannelToNext() { if (smartTvDevice.deviceStatus == "on") smartTvDevice.nextChannel() }
    fun changeTvChannelToPrevious() { if (smartTvDevice.deviceStatus == "on") smartTvDevice.previousChannel() }
    fun increaseLightBrightness() { if (smartLightDevice.deviceStatus == "on") smartLightDevice.increaseBrightness() }
    fun decreaseLightBrightness() { if (smartLightDevice.deviceStatus == "on") smartLightDevice.decreaseBrightness() }
    fun printSmartTvInfo() = smartTvDevice.printDeviceInfo()
    fun printSmartLightInfo() = smartLightDevice.printDeviceInfo()
    fun turnOffAllDevices() { turnOffTv(); turnOffLight() }
}

fun main() {
    val home = SmartHome(SmartTvDevice("Android TV", "Entertainment"), SmartLightDevice("Google Light", "Utility"))
    home.printSmartTvInfo()
    home.printSmartLightInfo()
    home.decreaseTvVolume() // Apagado: el volumen no cambia.
    check(home.smartTvDevice.speakerVolume == 2)
    home.turnOnTv(); home.turnOnTv(); home.turnOnLight()
    check(home.deviceTurnOnCount == 2)
    home.increaseTvVolume(); home.decreaseTvVolume()
    home.changeTvChannelToNext(); home.changeTvChannelToPrevious()
    home.increaseLightBrightness(); home.decreaseLightBrightness()
    println("Dispositivos encendidos: ${home.deviceTurnOnCount}")
    home.turnOffAllDevices(); home.turnOffAllDevices()
    check(home.deviceTurnOnCount == 0)
    println("Dispositivos encendidos: ${home.deviceTurnOnCount}")
}
