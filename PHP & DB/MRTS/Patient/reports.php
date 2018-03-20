<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
 
	$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
users.ID,
labs.LabID,
labs.LabName,
reports.ReportType,
reports.ReportName,
reports.Description,
reports.Pic,
reports.Date,
users.LastName,
users.FirstName,
users.MiddleName
FROM
reports
INNER JOIN users ON users.ID = reports.DoctorID
INNER JOIN labs ON labs.LabID = reports.LabID
where reports.PatientID ='$p_id'";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('DoctorID'=>$row[0],
'LabID'=>$row[1],
'LabName'=>$row[2],
'ReportType'=>$row[3],
'ReportName'=>$row[4],
'Description'=>$row[5],

'Pic'=>$row[6],
'Date'=>$row[7],
'D_Last_name'=>$row[8],
'D_first_name'=>$row[9],

'D_mid_name'=>$row[10]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>