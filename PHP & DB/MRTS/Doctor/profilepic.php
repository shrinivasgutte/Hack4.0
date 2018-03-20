<?php

if(isset($_POST['ID'])) {
    $ID = $_POST['ID'];
}
else
    die("No Data");


$oldimg='../Temp/'.$ID."jpg";
$newimg='../'.$ID.'/'. $ID.'jpg';
rename($oldimg, $newimg);

echo "Success ";