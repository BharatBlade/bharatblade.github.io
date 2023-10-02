#include<Date.au3>
#include <IE.au3>
#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
Local $DIcheck = False
Local $DUcheck = False
Local $BLUcheck = False
Local $PTTcheck = False
Local $DIcount = 0
Local $DUcount = 0
Local $BLUcount = 0
Local $PTTcount = 0
Local $count = 0
Local $Start = False
Local $DIBeginDate = ""
Local $DIEndDate = ""
Local $DUBeginDate = ""
Local $DUEndDate = ""
Local $BLUBeginDate = ""
Local $BLUEndDate = ""
Local $PTTBeginDate = ""
Local $PTTEndDate = ""
Local $oSubmit1 = ""
Local $oSubmit2 = ""
Local $Name = ""


$d = @MON - 1
$begindate = 1

If $d = 1 Or $d = 3 Or $d = 5 Or $d = 7 Or $d = 8 Or $d = 10 Or $d = 12 Then
   $enddate = 31
EndIf
If $d = 4 Or $d = 6 Or $d = 9 Or $d = 11 Then
   $enddate = 30
EndIf
If $d = 2 Then
   $enddate = 28
EndIf
$monthlyreportdate = $d & "/" & $begindate & "/" & @YEAR
$monthlyreportdate2 = $d & "/" & $enddate & "/" & @YEAR


AutoReportServer()
   StartUp()
   Local $oIE = _IEAttach("Vocera Report Console | Report Package Setup")
If $DIcheck = True Then
   DeviceInventory()
EndIf
If $DUcheck = True Then
   DeviceUsage()
EndIf
If $BLUcheck = True Then
   BadgeLastUsed()
EndIf
If $PTTcheck = True Then
   PushToTalk()
EndIf


Func StartUp()
Sleep(5000)
EndFunc
Func AutoReportServer()
; Create a GUI with various controls.
Local $hGUI = GUICreate("Automated Report Console", 600, 600)

; Create a checkbox control.
Local $DeviceInventory = GUICtrlCreateCheckbox("Device Inventory - Detail (Device Management)", 10, 10, 200, 25)
   Local $DIM = GUICtrlCreateCheckbox("Last Month", 30, 30, 200, 25)
   Local $DIDateRange = GUICtrlCreateCheckbox("Specific Date Range", 30, 50, 200, 25)
   Local $DIComboBox = GUICtrlCreateCombo("CSV", 30, 70, 185, 20)
   GUICtrlSetData($DIComboBox, "RPT|PDF|Excel|Excel Data-Only|Excel - Editable|RTF", "CSV")
Local $DeviceUsage = GUICtrlCreateCheckbox("Device Usage (Device Management)", 10, 90, 200, 25)
   Local $DUM = GUICtrlCreateCheckbox("Last Month", 30, 110, 200, 25)
   Local $DUDateRange = GUICtrlCreateCheckbox("Specific Date Range", 30, 130, 200, 25)
   Local $DUComboBox = GUICtrlCreateCombo("CSV", 30, 150, 185, 20)
   GUICtrlSetData($DUComboBox, "RPT|PDF|Excel|Excel Data-Only|Excel - Editable|RTF", "CSV")
Local $BadgeLastUsed = GUICtrlCreateCheckbox("Badge Last Used", 10, 170, 200, 25)
   Local $BLUM = GUICtrlCreateCheckbox("Last Month", 30, 190, 200, 25)
   Local $BLUDateRange = GUICtrlCreateCheckbox("Specific Date Range", 30, 210, 200, 25)
   ;Local $BLUComboBox = GUICtrlCreateCombo("CSV", 30, 230, 185, 20)
   ;GUICtrlSetData($BLUComboBox, "RPT|PDF|Excel|Excel Data-Only|Excel - Editable|RTF", "CSV")
Local $PushToTalk = GUICtrlCreateCheckbox("Push To Talk", 10, 250, 200, 25)
   Local $PTTM = GUICtrlCreateCheckbox("Last Month", 30, 270, 200, 25)
   Local $PTTDateRange = GUICtrlCreateCheckbox("Specific Date Range", 30, 290, 200, 25)
   Local $PTTComboBox = GUICtrlCreateCombo("CSV", 30, 310, 185, 20)
   GUICtrlSetData($PTTComboBox, "RPT|PDF|Excel|Excel Data-Only|Excel - Editable|RTF", "CSV")

Local $idClose = GUICtrlCreateButton("OK", 500, 500, 85, 25)
; Display the GUI.
GUISetState(@SW_SHOW, $hGUI)

; Loop until the user exits.
     While 1
        Switch GUIGetMsg()
            Case $idClose
				ExitLoop
			Case $GUI_EVENT_CLOSE
                Exit

			Case $DeviceInventory
               If _IsChecked($DeviceInventory) Then
                    $DIcheck = True
					If GUICtrlRead(6)="RPT" Then
					   $DIcount = 0
					EndIf
					If GUICtrlRead(6)="PDF" Then
					   $DIcount = 1
					EndIf
					If GUICtrlRead(6)="Excel" Then
					   $DIcount = 2
					EndIf
					If GUICtrlRead(6)="Excel Data-Only" Then
					   $DIcount = 3
					EndIf
					If GUICtrlRead(6)="Excel - Editable" Then
					   $DIcount = 4
					EndIf
					If GUICtrlRead(6)="RTF" Then
					   $DIcount = 5
					EndIf
					If GUICtrlRead(6)="CSV" Then
					   $DIcount = 6
					EndIf
               Else
                    $DIcheck = False
			   EndIf
			Case $DIM
               If _IsChecked($DIM) Then
					GUICtrlSetState($DIDateRange, 4)
				    $DIBeginDate = $monthlyreportdate
					$DIEndDate = $monthlyreportdate2
               Else
					 Local $DIBeginDate = ""
					 Local $DIEndDate = ""
               EndIf
			Case $DIDateRange
			   If _IsChecked($DIDateRange) Then
					 GUICtrlSetState($DIM, 4)
					 $DIBeginDate = InputBox("Enter Start Date", "Enter Start Date")
					 $DIEndDate = InputBox("Enter End Date", "Enter End Date")
			   Else
					 Local $DIBeginDate = ""
					 Local $DIEndDate = ""
			   EndIf


			Case $DeviceUsage
               If _IsChecked($DeviceUsage) Then
                    $DUcheck = True
				    If GUICtrlRead(10)="RPT" Then
					   $DIcount = 0
					EndIf
					If GUICtrlRead(10)="PDF" Then
					   $DIcount = 1
					EndIf
					If GUICtrlRead(10)="Excel" Then
					   $DIcount = 2
					EndIf
					If GUICtrlRead(10)="Excel Data-Only" Then
					   $DIcount = 3
					EndIf
					If GUICtrlRead(10)="Excel - Editable" Then
					   $DIcount = 4
					EndIf
					If GUICtrlRead(10)="RTF" Then
					   $DIcount = 5
					EndIf
					If GUICtrlRead(10)="CSV" Then
					   $DIcount = 6
					EndIf
               Else
                    $DUcheck = False
			   EndIf
			Case $DUM
               If _IsChecked($DUM) Then
					GUICtrlSetState($DIDateRange, 4)
				    $DUBeginDate = $monthlyreportdate
					$DUEndDate = $monthlyreportdate2
               Else
					 Local $DUBeginDate = ""
					 Local $DUEndDate = ""
               EndIf
			Case $DUDateRange
			   If _IsChecked($DUDateRange) Then
					 GUICtrlSetState($DUM, 4)
					 $DUBeginDate = InputBox("Enter Start Date", "Enter Start Date")
					 $DUEndDate = InputBox("Enter End Date", "Enter End Date")
			   Else
					 Local $DUBeginDate = ""
					 Local $DUEndDate = ""
			   EndIf


			Case $BadgeLastUsed
               If _IsChecked($BadgeLastUsed) Then
                    $BLUcheck = True
               Else
                    $BLUcheck = False
			   EndIf
			Case $BLUM
               If _IsChecked($BLUM) Then
					GUICtrlSetState($BLUDateRange, 4)
				    $BLUBeginDate = $monthlyreportdate
					$BLUEndDate = $monthlyreportdate2
               Else
					 Local $BLUBeginDate = ""
					 Local $BLUEndDate = ""
               EndIf
			Case $BLUDateRange
			   If _IsChecked($BLUDateRange) Then
					 GUICtrlSetState($BLUM, 4)
					 $BLUBeginDate = InputBox("Enter Start Date", "Enter Start Date")
					 $BLUEndDate = InputBox("Enter End Date", "Enter End Date")
			   Else
					 Local $BLUBeginDate = ""
					 Local $BLUEndDate = ""
			   EndIf


			Case $PushToTalk
               If _IsChecked($PushToTalk) Then
                    $PTTcheck = True
					If GUICtrlRead(18)="RPT" Then
					   $DIcount = 0
					EndIf
					If GUICtrlRead(18)="PDF" Then
					   $DIcount = 1
					EndIf
					If GUICtrlRead(18)="Excel" Then
					   $DIcount = 2
					EndIf
					If GUICtrlRead(18)="Excel Data-Only" Then
					   $DIcount = 3
					EndIf
					If GUICtrlRead(18)="Excel - Editable" Then
					   $DIcount = 4
					EndIf
					If GUICtrlRead(18)="RTF" Then
					   $DIcount = 5
					EndIf
					If GUICtrlRead(18)="CSV" Then
					   $DIcount = 6
					EndIf
               Else
                    $PTTcheck = False
			   EndIf
			Case $PTTM
               If _IsChecked($PTTM) Then
					GUICtrlSetState($PTTDateRange, 4)
				    $PTTBeginDate = $monthlyreportdate
					$PTTEndDate = $monthlyreportdate2
               Else
					 Local $PTTBeginDate = ""
					 Local $PTTEndDate = ""
               EndIf
			Case $PTTDateRange
			   If _IsChecked($PTTDateRange) Then
					 GUICtrlSetState($PTTM, 4)
					 $PTTBeginDate = InputBox("Enter Start Date", "Enter Start Date")
					 $PTTEndDate = InputBox("Enter End Date", "Enter End Date")
			   Else
					 Local $PTTBeginDate = ""
					 Local $PTTEndDate = ""
			   EndIf





        EndSwitch
    WEnd

; Delete the previous GUI and all controls.
GUIDelete($hGUI)
EndFunc


Func _IsChecked($idControlID)
    Return BitAND(GUICtrlRead($idControlID), $GUI_CHECKED) = $GUI_CHECKED
 EndFunc   ;==>_IsChecked


Func DeviceInventory()
$count = $DIcount
$Name = "Device Inventory - Detail (Device Management)"
Local $oSubmit1 = _IEGetObjByName($oIE, "device_management_link")
_IEAction($oSubmit1, "click")
Local $i = 0
WinWait("Vocera Report Console | Device Management Reports")
Local $oIE2 = _IEAttach("Vocera Report Console | Device Management Reports")
Sleep(10000)
Local $oForm2 = _IEFormGetObjByName($oIE2, "form1")
Sleep(10000)
_IEFormElementRadioSelect($oForm2, "Device Inventory - Detail (Device Management)", "reportname", 1, "byValue")
Sleep(5000)
$oSubmit2 = _IEGetObjByName($oIE, "save")
Sleep(2000)
_IEAction($oSubmit2, "click")
WinWaitActive("Vocera Report Console | Parameters")
Sleep(5000)
$oIE2 = _IEAttach("Vocera Report Console | Parameters")
Sleep(5000)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_from"),$DIBeginDate)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_to"),$DIEndDate)
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "siteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "typeDiscreteValue"), "All Device Types", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "ownerssiteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "owningDiscreteValue"), "All Owners", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata37"),"click")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "statusDiscreteValue"), "Active", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata39"),"click")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "statusDiscreteValue"), "Follow Up", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata39"),"click")
_IEAction(_IEGetObjByName($oIE2, "generate"),"click")
Crystal()
$oIE2 = _IEAttach("Vocera Report Console | Device Management Reports")
EndFunc

Func DeviceUsage()
$count = $DUcount
$Name = "Device Usage (Device Management)"
Local $oSubmit1 = _IEGetObjByName($oIE, "device_management_link")
_IEAction($oSubmit1, "click")
Local $i = 0
WinWait("Vocera Report Console | Device Management Reports")
$oIE2 = _IEAttach("Vocera Report Console | Device Management Reports")
Sleep(10000)
$oForm2 = _IEFormGetObjByName($oIE2, "form1")
Sleep(10000)
_IEFormElementRadioSelect($oForm2, "Device Usage (Device Management)", "reportname", 1, "byValue")
Sleep(5000)
$oSubmit2 = _IEGetObjByName($oIE, "save")
Sleep(2000)
_IEAction($oSubmit2, "click")
WinWaitActive("Vocera Report Console | Parameters")
Sleep(5000)
$oIE2 = _IEAttach("Vocera Report Console | Parameters")
Sleep(5000)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_from"),$DUBeginDate)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_to"),$DUEndDate)
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "siteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "typeDiscreteValue"), "All Device Types", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "ownerssiteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "owningDiscreteValue"), "All Owners", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata35"),"click")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "statusDiscreteValue"), "Active", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata37"),"click")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "statusDiscreteValue"), "Follow Up", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata37"),"click")
_IEAction(_IEGetObjByName($oIE2, "generate"),"click")
Crystal()
$oIE2 = _IEAttach("Vocera Report Console | Device Management Reports")
EndFunc

Func BadgeLastUsed()
Local $oSubmit1 = _IEGetObjByName($oIE, "export_data_link")
_IEAction($oSubmit1, "click")
$Name = "Badge Last Used"
Local $i = 0
WinWait("Vocera Report Console | Export Data Reports")
Local $oIE2 = _IEAttach("Vocera Report Console | Export Data Reports")
Sleep(10000)
Local $oForm2 = _IEFormGetObjByName($oIE2, "form1")
Sleep(10000)
_IEFormElementRadioSelect($oForm2, "Data - Badge Last Used", "reportname", 1, "byValue")
Sleep(5000)
$oSubmit2 = _IEGetObjByName($oIE2, "save")
Sleep(2000)
_IEAction($oSubmit2, "click")
WinWaitActive("Vocera Report Console | Parameters")
Sleep(5000)
Local $oIE4 = _IEAttach("Vocera Report Console | Parameters")
Sleep(5000)
_IEFormElementSetValue(_IEGetObjById($oIE4,"datepicker_from"),$BLUBeginDate)
_IEFormElementSetValue(_IEGetObjById($oIE4,"datepicker_to"),$BLUEndDate)
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE4, "pageCrystalForm"), "siteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE4, "pageCrystalForm"), "deptDiscreteValue"), "All Departments", 1, "byText")
_IEAction(_IEGetObjByName($oIE4, "adddata7"),"click")
_IEAction(_IEGetObjByName($oIE4, "export"),"click")
Local $hWnd4 = WinWaitActive("Message from webpage")
ControlClick($hWnd4, "", "[CLASS:Button; INSTANCE:1]")
ControlSend("Vocera Report Console | Parameters", "", "[CLASS:Frame Tab; INSTANCE:1]", "^j")
Sleep(2000)
ControlFocus("View Downloads", "", "[CLASS:DirectUIHWND; INSTANCE:1]")
ControlSend("View Downloads", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{RIGHT 2}")
Sleep(2000)
ControlSend("View Downloads", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{DOWN 2}")
Sleep(2000)
ControlSend("View Downloads", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{ENTER}")
Sleep(2000)
Local $hWnd = WinWait("Save As", "")
Sleep(2000)
ControlClick("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "right")
ControlSend("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "e")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "^a")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "{DEL}")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", @MON&"_"&@MDAY&"_"&@YEAR&" "&$Name)
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", @DesktopDir)
Sleep(2000)
ControlClick($hWnd, "", "Button1")
$oIE2 = _IEAttach("Vocera Report Console | Export Data Reports")
EndFunc

Func PushToTalk()
$count = $PTTcount
Local $oSubmit1 = _IEGetObjByName($oIE, "call_logs_link")
_IEAction($oSubmit1, "click")
$Name = "Push to Talk"
Sleep(3000)
$oSubmit2 = _IEGetObjByName($oIE, "save")
_IEAction($oSubmit2, "click")
WinWaitActive("Vocera Report Console | Parameters")
Sleep(5000)
$oIE2 = _IEAttach("Vocera Report Console | Parameters")
Sleep(5000)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_from"),$PTTBeginDate)
_IEFormElementSetValue(_IEGetObjById($oIE2,"datepicker_to"),$PTTEndDate)
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "siteDiscreteValue"), "Downtown", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "deptDiscreteValue"), "All Departments", 1, "byText")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE2, "pageCrystalForm"), "activityDiscreteValue"), "Push-to-Talk", 1, "byText")
_IEAction(_IEGetObjByName($oIE2, "adddata21"),"click")
_IEAction(_IEGetObjByName($oIE2, "adddata38"),"click")
_IEAction(_IEGetObjByName($oIE2, "generate"),"click")
Crystal()
$oIE2 = _IEAttach("Vocera Report Console | Call Log Reports")
EndFunc

Func Crystal()
   WinWaitActive("Crystal Reports Viewer")
   $oIE2 = _IEAttach("Crystal Reports Viewer")
   Sleep(5000)
   _IEAction(_IEGetObjById($oIE2,"CrystalViewer_toptoolbar_export"),"click")
   Sleep(2000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{ENTER}")
   Sleep(1000)
   Local $j = 0
   While($j < $count)
	  ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{DOWN}")
	  Sleep(1000)
	  $j = $j + 1
   WEnd
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{ENTER}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{ENTER}")
   Sleep(5000)
   While(StatusbarGetText("Crystal Reports Viewer")<>"")
	  Sleep(1000)
   WEnd
   $i = 0
   While $i < 16
   ControlSend("Crystal Reports Viewer", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "{TAB}")
   Sleep(1000)
   $i = $i + 1
   WEnd
   ControlSend("Crystal Reports Viewer", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{TAB}")
   Sleep(2000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{TAB}")
   Sleep(2000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{TAB}")
   Sleep(2000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{DOWN 2}")
   Sleep(2000)
   ControlSend("Crystal Reports Viewer", "", "[CLASS:DirectUIHWND; INSTANCE:1]", "{ENTER}")
   Sleep(2000)
   Local $hWnd = WinWait("Save As", "")
   Sleep(2000)
   ControlClick("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "right")
   ControlSend("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "e")
   Sleep(1000)
   ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "^a")
   ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "{DEL}")
   ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", @MON&"_"&@MDAY&"_"&@YEAR&" "&$Name)
   Sleep(1000)
   ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", @DesktopDir)
   Sleep(2000)
   ControlClick($hWnd, "", "Button1")
   Sleep(20000)
   WinClose ("Crystal Reports Viewer")
   Sleep(5000)
EndFunc