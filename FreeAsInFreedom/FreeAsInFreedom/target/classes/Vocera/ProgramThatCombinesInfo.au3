#include<Date.au3>
#include <IE.au3>
#include <GUIConstantsEx.au3>
#include <MsgBoxConstants.au3>
#include <Excel.au3>
MsgBox(0,"Check Your IE","Does the first tab have the admin console logged in, and the second tab have self serve logged in?")
Local $oIE = _IEAttach("Vocera Administrator | Status Monitor")
Local $oObject = _IEGetObjByName($oIE,"maintenance_link")
_IEAction($oObject, "click")
_IELoadWait($oIE)
$oObject = _IEGetObjByName($oIE,"tab_3")
_IEAction($oObject, "click")
_IEFormElementOptionSelect(_IEFormElementGetObjByName(_IEFormGetObjByName($oIE, "form1"), "siteList"), "Downtown", 1, "byText")
Local $oForm = _IEFormGetObjByName($oIE, "form1")
_IEFormElementRadioSelect($oForm, "devices", "exportType", 1, "byValue")
$oObject = _IEGetObjByName($oIE,"exportfile")
_IEAction($oObject, "click")
WinWaitActive("File Download")
Sleep(1000)
Local $hWnd1 = WinWait("File Download", "", 20)
ControlClick($hWnd1, "", "Button2")
Local $hWnd = WinWait("Save As", "", 20)
ControlClick("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "right")
ControlSend("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "e")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "^a")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "{DEL}")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", @MON&"_"&@MDAY&"_"&@YEAR&"_devices.csv")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", "Desktop\combine")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", "{ENTER}")
Sleep(1000)
ControlClick($hWnd, "", "Button1")
Local $hWnd2 = WinWait("Download complete", "", 10)
;ControlClick($hWnd2, "", "Button4")
ControlSend("Vocera Administrator | Maintenance", "", "[CLASS:Internet Explorer_Server; INSTANCE:1]", "^{TAB}")
Sleep(1000)
$oIE = _IEAttach("SelfServe")
$oObject = _IEGetObjByName($oIE,"applications")
_IEAction($oObject, "click")
_IELoadWait($oIE)
_IELinkClickByText($oIE, "Vocera")
_IELoadWait($oIE)
_IELinkClickByText($oIE, "Create Extract")
WinWaitActive("File Download")
Sleep(1000)
$hWnd1 = WinWait("File Download", "", 20)
ControlClick($hWnd1, "", "Button2")
Sleep(2000)
$hWnd = WinWait("Save As", "", 20)
Sleep(2000)
ControlClick("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "right")
ControlSend("Save As", "", "[CLASS:ToolbarWindow32; INSTANCE:3]", "e")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "^a")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", "{DEL}")
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:1]", @MON&"_"&@MDAY&"_"&@YEAR&"_voceraData.xls")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", "Desktop\combine")
Sleep(1000)
ControlSend("Save As", "", "[CLASS:Edit; INSTANCE:2]", "{ENTER}")
Sleep(1000)
ControlClick($hWnd, "", "Button1")
$hWnd2 = WinWait("Download complete", "", 20)
ControlClick($hWnd2, "", "Button4")
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;