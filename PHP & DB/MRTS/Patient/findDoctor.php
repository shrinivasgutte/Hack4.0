<?php
include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$State=$query['State']; // Fetching URL Parameter
	$City=$query['City']; // Fetching URL Parameter
	$Qualification=$query['Qualification']; // Fetching URL Parameter
	
 
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
	users.ID,
doctor.Qualification,
doctor.Speciality,
doctor.HospitalID,
hospital.`Name`,
hospital.Address,
hospital.ContactNo,
hospital.EmergencyNo,
hospital.WorkingHours,
users.LastName,
users.FirstName,
users.MiddleName,
hospital.HospitalID,
users.ContactNo,
users.Gender,
users.Email


FROM
users
INNER JOIN doctor ON users.ID = doctor.DoctorID
INNER JOIN hospital ON doctor.HospitalID = hospital.HospitalID
WHERE
users.State='$State'  and  users.City='$City' and doctor.Speciality='$Qualification'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('DoctorID'=>$row[0],
'Qualification'=>$row[1],
'Speciality'=>$row[2],

'HospitalName'=>$row[4],
'H_Address'=>$row[5],
'H_ContactNo'=>$row[6],
'H_EmergencyNo'=>$row[7],
'H_WorkingHours'=>$row[8],
'D_Last_name'=>$row[9],
'D_first_name'=>$row[10],

'D_mid_name'=>$row[11],
'HospitalID'=>$row[12],
'D_ContactNo'=>$row[13],
'D_Gender'=>$row[14],
'D_Email'=>$row[15]
));
}
 //'HospitalID'=>$row[3],
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>