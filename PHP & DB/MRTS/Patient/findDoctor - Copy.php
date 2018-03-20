<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
 
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	 /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
familymember.PrimaryID,
familymember.MemberID,
familymember.Primary_EmergencyAction,
familymember.Secondary_EmergencyAction,
familymember.OtherSenderID,
familymember.Location,
familymember.Date,

primer.LastName,
primer.FirstName,
primer.MiddleName,
primer.ContactNo,
member.LastName,
member.FirstName,
member.MiddleName,
member.ContactNo,
other.LastName,
other.FirstName,
other.MiddleName,
other.ContactNo
FROM
familymember
INNER JOIN users AS primer ON familymember.PrimaryID = primer.ID
INNER JOIN users AS member ON familymember.MemberID = member.ID
INNER JOIN users AS other ON familymember.OtherSenderID = other.ID where familymember.PrimaryID='$p_id' and 
familymember.Secondary_EmergencyAction='Emergency' OR familymember.MemberID='$p_id' and 
familymember.Primary_EmergencyAction='Emergency'
";
	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('PrimaryID'=>$row[0],
'MemberID'=>$row[1],
'Primary_EmergencyAction'=>$row[2],
'Secondary_EmergencyAction'=>$row[3],
'OtherSenderID'=>$row[4],
'Location'=>$row[5],
'Date'=>$row[6],
'PrimaryLastName'=>$row[7],
'PrimaryFirstName'=>$row[8],
'PrimaryMiddleName'=>$row[9],
'PrimaryContactNo'=>$row[10],

'MemberLastName'=>$row[11],
'MemberFirstName'=>$row[12],
'MemberMiddleName'=>$row[13],
'MemberContactNo'=>$row[14],

'OtherLastName'=>$row[15],
'OtherFirstName'=>$row[16],
'OtherMiddleName'=>$row[17],
'OtherContactNo'=>$row[18]
));
}
 //'HospitalID'=>$row[3],
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>