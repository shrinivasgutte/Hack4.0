 <?php
   
include '../db.php';
	$w_pID=$_POST['whereNext'];
   $tab_name=$_POST['tableName'];
$col_name=$_POST['columnName']; 
$val=$_POST['value']; 
  $p_id_col_name=$_POST['PatientIDColumnName']; 
  $r=$_POST['ReqID'];

//$w_pID="11223344";
 //  $tab_name="users";
  // $col_name="ContactNo";
//$val="143143144";
//$p_id_col_name="ID";



	
	
$sql="UPDATE `$tab_name` SET  `$col_name`='$val' WHERE `$p_id_col_name`='$w_pID'";
$conn->query($sql);

$sql = "UPDATE `request` SET `Accept`= 'Accept' WHERE `RequestID` ='$r'";
        $conn->query($sql);

echo "success a";

 ?>