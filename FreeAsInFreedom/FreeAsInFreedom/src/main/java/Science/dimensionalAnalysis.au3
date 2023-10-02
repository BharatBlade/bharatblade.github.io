#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
DA()
Func DA()
Local $hGUI = GUICreate("Converter", 200, 200)
GUISetState(@SW_SHOW, $hGUI)
Local $number = GUICtrlCreateInput("", 25 , 25, 50)
Local $measurements = GUICtrlCreateCombo("length", 25, 150, 80)
GUICtrlSetData($measurements, "area|volume|mass|temperature|time|speed", "length")
Local $convert = GUICtrlCreateButton("Convert", 125, 150)
Local $idComboBox
Local $idComboBox2
Local $idComboBox3
Local $idComboBox4

While 1
        Switch GUIGetMsg()
			Case $idClose
			   Exit
			Case $GUI_EVENT_CLOSE
               Exit
			Case $measurements
			   If GUICtrlRead($measurements) == "length" Then
				  GUICtrlDelete($idComboBox)
				  GUICtrlDelete($idComboBox2)
				  $idComboBox = GUICtrlCreateCombo("em", 100, 25, 100)
				  $idComboBox2 = GUICtrlCreateCombo("em", 100, 50, 100)
				  Local $length[22] = ["em", "Pm", "tm", "gm", "Mm", "km", "hm", "Dm", "m ", "dm", "cm", "mm", "um", "nm", "pm", "fm", "am", "zm", "ft", "in", "yd", "mi"]
				  Local $lengthNum[22] = [10^18, 10^15, 10^12, 10^9, 10^6, 10^3, 10^2, 10^1, 10^0, 10^-1, 10^-2, 10^-3, 10^-6, 10^-9, 10^-12, 10^-15, 10^-18, 10^-21, (2.54/(10^2))*12, 2.54/(10^2), (2.54/(10^2))*12*3, (2.54/(10^2))*12*5280]
				  GUICtrlSetData($idComboBox, "Pm|tm|gm|Mm|km|hm|Dm|m |dm|cm|mm|um|nm|pm|fm|am|zm|ft|in|yd|mi", "m ")
				  GUICtrlSetData($idComboBox2, "Pm|tm|gm|Mm|km|hm|Dm|m |dm|cm|mm|um|nm|pm|fm|am|zm|ft|in|yd|mi", "m ")

			   EndIf
			   If GUICtrlRead($measurements) == "area" Then

			   EndIf
			   If GUICtrlRead($measurements) == "volume" Then
				  GUICtrlDelete($idComboBox)
				  GUICtrlDelete($idComboBox2)
				  $idComboBox = GUICtrlCreateCombo("m^3", 100, 25, 100)
				  $idComboBox2 = GUICtrlCreateCombo("m^3", 100, 50, 100)
				  Local $length[12] = ["m^3", "cm^3", "in^3", "ft^3", "yd^3", "mL", "cL", "dL", "L", "fl oz", "pints", "gal"]
				  Local $lengthNum[12] = [1, (100^3), (100/2.54)^3, (((2.54/100)*12)^3)^-1, (((2.54/100)*12*3)^3)^-1, (100^3), (100^3)/10, (100^3)/100, (100^3)/1000, 33814.0227, 2113.37642, 264.172052]
				  GUICtrlSetData($idComboBox, "cm^3|in^3|ft^3|yd^3|mL|cL|dL|L|fl oz|pints|gal", "m^3")
				  GUICtrlSetData($idComboBox2, "cm^3|in^3|ft^3|yd^3|mL|cL|dL|L|fl oz|pints|gal", "m^3")
			   EndIf
			   If GUICtrlRead($measurements) == "mass" Then

			   EndIf
			   If GUICtrlRead($measurements) == "temperature" Then
				  GUICtrlDelete($idComboBox)
				  GUICtrlDelete($idComboBox2)
				  $idComboBox = GUICtrlCreateCombo("F", 100, 25, 100)
				  $idComboBox2 = GUICtrlCreateCombo("F", 100, 50, 100)
				  Local $length[3] = ["F", "C", "K"]
				  GUICtrlSetData($idComboBox, "C|K", "F")
				  GUICtrlSetData($idComboBox2, "C|K", "F")
			   EndIf
			   If GUICtrlRead($measurements) == "time" Then
				  GUICtrlDelete($idComboBox)
				  GUICtrlDelete($idComboBox2)
				  $idComboBox = GUICtrlCreateCombo("sec", 100, 25, 100)
				  $idComboBox2 = GUICtrlCreateCombo("sec", 100, 50, 100)
				  Local $length[7] = ["sec","min","hrs","days","weeks","months","years"]
				  Local $lengthNum[7] = [1, 60, 3600, 24*3600, 7*24*3600, 4*7*24*3600, 365*24*3600]
				  GUICtrlSetData($idComboBox, "min|hrs|days|weeks|months|years", "sec")
				  GUICtrlSetData($idComboBox2, "min|hrs|days|weeks|months|years", "sec")
			   EndIf
			   If GUICtrlRead($measurements) == "speed" Then
				  GUICtrlDelete($idComboBox)
				  GUICtrlDelete($idComboBox2)
				  GUICtrlDelete($idComboBox3)
				  GUICtrlDelete($idComboBox4)
				  $idComboBox = GUICtrlCreateCombo("m", 100, 25, 45)
				  $idComboBox2 = GUICtrlCreateCombo("m", 100, 50, 45)
				  GUICtrlCreateLabel("/", 147, 25, 3)
				  GUICtrlCreateLabel("/", 147, 50, 3)
				  $idComboBox3 = GUICtrlCreateCombo("sec", 155, 25, 45)
				  $idComboBox4 = GUICtrlCreateCombo("sec", 155, 50, 45)
				  Local $length[7] = ["sec","min","hrs","days","weeks","months","years"]
				  Local $lengthNum[7] = [1, 60, 3600, 24*3600, 7*24*3600, 4*7*24*3600, 365*24*3600]
				  Local $length2[22] = ["em", "Pm", "tm", "gm", "Mm", "km", "hm", "Dm", "m ", "dm", "cm", "mm", "um", "nm", "pm", "fm", "am", "zm", "ft", "in", "yd", "mi"]
				  Local $lengthNum2[22] = [10^18, 10^15, 10^12, 10^9, 10^6, 10^3, 10^2, 10^1, 10^0, 10^-1, 10^-2, 10^-3, 10^-6, 10^-9, 10^-12, 10^-15, 10^-18, 10^-21, (2.54/(10^2))*12, 2.54/(10^2), (2.54/(10^2))*12*3, (2.54/(10^2))*12*5280]
				  GUICtrlSetData($idComboBox, "Pm|tm|gm|Mm|km|hm|Dm|m |dm|cm|mm|um|nm|pm|fm|am|zm|ft|in|yd|mi", "m ")
				  GUICtrlSetData($idComboBox2, "Pm|tm|gm|Mm|km|hm|Dm|m |dm|cm|mm|um|nm|pm|fm|am|zm|ft|in|yd|mi", "m ")
				  GUICtrlSetData($idComboBox3, "min|hrs|days|weeks|months|years", "sec")
				  GUICtrlSetData($idComboBox4, "min|hrs|days|weeks|months|years", "sec")
			   EndIf

			Case $convert
			   Local $rawUnit = GUICtrlRead($idComboBox)
			   Local $rawUnitNum = 0
			   Local $finalUnit = GUICtrlRead($idComboBox3)
			   Local $finalUnitNum = 0
			   Local $raw = GUICtrlRead($number)
			   Local $rawUnit2 = GUICtrlRead($idComboBox2)
			   Local $rawUnitNum2 = 0
			   Local $finalUnit2 = GUICtrlRead($idComboBox4)
			   Local $finalUnitNum2 = 0
			   Local $raw2 = GUICtrlRead($number)

			   If GUICtrlRead($measurements) == "temperature" Then
				  If $rawUnit == "F" Then
					 If $finalUnit == "C" Then
						Local $answer = GUICtrlCreateLabel((5/9)*($raw - 32) & $finalUnit, 10 , 100, 150, 50)
					 EndIf
					 If $finalUnit == "K" Then
						Local $answer = GUICtrlCreateLabel((5/9)*($raw - 32) + 273 & $finalUnit, 10 , 100, 150, 50)
					 EndIf
				  EndIf
				  If $rawUnit == "C" Then
					 If $finalUnit == "F" Then
						Local $answer = GUICtrlCreateLabel((9/5)*$raw + 32 & $finalUnit, 10 , 100, 150, 50)
					 EndIf
					 If $finalUnit == "K" Then
						Local $answer = GUICtrlCreateLabel($raw + 273 & $finalUnit, 10 , 100, 150, 50)
					 EndIf
				  EndIf
				  If $rawUnit == "K" Then
					 If $finalUnit == "F" Then
						Local $answer = GUICtrlCreateLabel((9/5)*($raw-273) + 32 & $finalUnit, 10 , 100, 150, 50)
					 EndIf
					 If $finalUnit == "C" Then
						Local $answer = GUICtrlCreateLabel($raw - 273 & $finalUnit, 10 , 100, 150, 50)
					 EndIf
				  EndIf
			   EndIf
			   If GUICtrlRead($measurements) == "length" Then
				  Local $i = 0
				  While $i < UBound($length)
					 If $rawUnit == $length[$i] Then
						$rawUnitNum = $lengthNum[$i]
					 EndIf
					 If $finalUnit == $length[$i] Then
						$finalUnitNum = $lengthNum[$i]
					 EndIf
					 $i = $i + 1
				  WEnd
				  Local $answer = GUICtrlCreateLabel($raw*($rawUnitNum/$finalUnitNum) & $finalUnit, 10 , 100, 150, 50)
			   EndIf
			   If GUICtrlRead($measurements) == "time" Then
				  Local $i = 0
				  While $i < UBound($length)
					 If $rawUnit == $length[$i] Then
						$rawUnitNum = $lengthNum[$i]
					 EndIf
					 If $finalUnit == $length[$i] Then
						$finalUnitNum = $lengthNum[$i]
					 EndIf
					 $i = $i + 1
				  WEnd
				  Local $answer = GUICtrlCreateLabel($raw*($rawUnitNum/$finalUnitNum) & $finalUnit, 10 , 100, 150, 50)
			   EndIf
			   If GUICtrlRead($measurements) == "volume" Then
				  Local $i = 0
				  While $i < UBound($length)
					 If $rawUnit == $length[$i] Then
						$rawUnitNum = $lengthNum[$i]
					 EndIf
					 If $finalUnit == $length[$i] Then
						$finalUnitNum = $lengthNum[$i]
					 EndIf
					 $i = $i + 1
				  WEnd
				  Local $answer = GUICtrlCreateLabel(($finalUnitNum/$rawUnitNum) & $finalUnit, 10 , 100, 150, 50)
			   EndIf
			   If GUICtrlRead($measurements) == "speed" Then
				  Local $i = 0
				  While $i < UBound($length)
					 If $rawUnit == $length[$i] Then
						$rawUnitNum = $lengthNum[$i]
					 EndIf
					 If $finalUnit == $length[$i] Then
						$finalUnitNum = $lengthNum[$i]
					 EndIf
					 $i = $i + 1
				  WEnd
				  Local $j = 0
				  While $j < UBound($length2)
					 If $rawUnit2 == $length2[$j] Then
						$rawUnitNum2 = $lengthNum2[$j]
					 EndIf
					 If $finalUnit2 == $length2[$j] Then
						$finalUnitNum2 = $lengthNum2[$j]
					 EndIf
					 $j = $j + 1
				  WEnd
				  ConsoleWrite($finalUnitNum)
				  ConsoleWrite($rawUnitNum)
				  ConsoleWrite($finalUnitNum2)
				  ConsoleWrite($rawUnitNum2)
				  Local $answer = GUICtrlCreateLabel($raw*($finalUnitNum/$rawUnitNum) & $finalUnit & "/" & ($finalUnitNum2/$rawUnitNum2) & $finalUnit2, 10 , 100, 150, 50)

			   EndIf



        EndSwitch
WEnd
EndFunc