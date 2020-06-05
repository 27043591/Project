package jb.tech.projectcarinfo;

import android.app.Activity;

public class AddCarsToSQL
{
    public AddCarsToSQL(Activity activity)
    {
        sqldatabase sql = new sqldatabase(activity);

        sql.add("12345", "2005", "FORD", "MONDEO", "Blue", "2.0 SEDAN AUT",
                "Saloon", "WF04XXGBB45G43593", "DAQ149", "43593", "Passenger Car/Van",
                "5", "1,999cc", "Petrol", "105kW", "4-geqr automatic", "Imported Built-Up",
                "Belgium","https://autovolostorage.blob.core.windows.net/advertimages-6515626/ford-mondeo-2003-676l.jpg");

        sql.add("12344", "1963", "MITSUBISHI", "ETERNA", "Blue", "2.0 SEDAN AUT",
                "Saloon", "7A8CJ0M0799013415", "ACD123", "6A11AH8815", "Passenger Car",
                "5", "1,829cc", "Petrol", "100gkW", "4-geqr automatic", "Imported Built-Up",
                "Japan","https://carsot.com/images/mitsubishi-eterna-vii-1992-1996-sedan-exterior-2.jpg");

 sql.add("12346", "2005", "FORD", "MONDEO", "GOLD", "2.0 SEDAN AUT",
                "Saloon", "WF04XXGBB45G44037", "DAQ148", "44037", "Passenger Car/Van",
                "5", "1,999cc", "Petrol", "105kW", "4-geqr automatic", "Imported Built-Up",
                "Belgium","https://autovolostorage.blob.core.windows.net/advertimages-6595650/ford-mondeo-2007-417l.jpg");

 sql.add("12347", "2000", "SUBARU", "LEGACY", "Silver", "B4",
                "Saloon", "7A8GF0B0703040487", "BDF234", "EJ20-772462", "Passenger Car/Van",
                "5", "1,999cc", "Petrol", "105kW", "4-geqr automatic", "Imported Built-Up",
                "Japan","https://d1tnneiliiskbl.cloudfront.net/jph/_search_img_catalog_10451010_200301.jpg");

        sql.add("12348", "1995", "NISSAN", "CEFIRO", "White", "B4",
                "Saloon", "7A8DH2A0704013630", "CCF345", "VQ32-014270A", "Passenger Car/Van",
                "5", "2,495cc", "Petrol", "105kW", "4-geqr automatic", "Imported Built-Up",
                "Japan","https://catalogphoto.goo-net.com/carphoto/10151014_200001a.jpg");


        sql.add("34567", "2016", "FACTORY BUILT", "BMW", "Grey", "M2",
                "Saloon", "WBS1H920X0V818345", "34567", "01849770", "Passenger Car/Van",
                "4", "2,979cc", "Petrol", "NA", "NA", "Imported Built-Up",
                "Germany","https://www.carjam.co.nz/images/400x222-upload-your-photo.svg");

        sql.add("AAD123", "2000", "FORD", "MONDEO", "Red", "GLX 2.0 SEDAN AUTOMA",
                "Saloon", "WF0FXXGBBFXD46541", "AAD123", "46541", "Passenger Car/Van",
                "5", "1,989cc", "Petrol", "97kW", "4-Gear Automatic", "Imported Built-Up",
                "Belgium","https://rc-resources.dotnous.com/vehicles/FORD/detail/dr-1658.png");


        sql.add("DAD123", "1996", "HONDA", "ORTHIA", "BLACK", "P",
                "Station Wagon", "7A88G850705015718", "DAD123", "B20B-1115494", "Passenger Car/Van",
                "5", "1,970cc", "Petrol", "NA", "NA", "Imported Built-Up",
                "Japan","https://d1tnneiliiskbl.cloudfront.net/jph/_search_img_catalog_10202009_200009.jpg");



        sql.add("PQ234", "1983", "TOYOTA", "TRUENO", "Red", "NA",
                "HatchBack", "NA", "PQ234", "EA-U", "Passenger Car/Van",
                "5", "1,500cc", "Petrol", "NA", "NA", "Unknown",
                "Japan","https://www.carjam.co.nz/images/400x222-upload-your-photo.svg");



        sql.add("PP456", "1982", "MITSUBISHI", "MIRAGE", "Red", "NA",
                "HatchBack", "NA", "PP456", "G11B", "Passenger Car/Van",
                "5", "1,410cc", "Petrol", "NA", "NA", "Unknown",
                "Japan","https://d1tnneiliiskbl.cloudfront.net/p/60/400/222/1256311.jpg");







    }
}
