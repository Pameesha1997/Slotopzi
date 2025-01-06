import './App.css';
import { Route, Switch, BrowserRouter as Router } from "react-router-dom"
import Landing from './pages/Landing'
import Signup from './pages/Signup'
import Login from './pages/Login';
import Service from './pages/serviceAdvisor/Dashboard'
import Test from './pages/serviceAdvisor/Test'

import Customer from './pages/customer/Index'
import VehicleDetails from './pages/customer/VehicleProfile'

import AdminDashboard from './pages/admin/AdminDashboard';
import AdminStaff from './pages/admin/AdminStaff';
import VehicleInfo from './pages/serviceAdvisor/VehicleInfo';
import Dashboard from './pages/technician/Dashboard';
import ServiceSummary from './pages/technician/ServiceSummary';
import ServiceProgress from './pages/technician/ServiceProgress';
import RepairHistory from './pages/customer/RepairHistory'
import Expenses from './pages/customer/Expenses'
import Profile from './pages/customer/Profile'
// import AddNew from './pages/serviceAdvisor/AddNew'
import AdminSection from './pages/admin/AdminSection';
import Testing from './pages/admin/Testing';
import DetailsForm from './pages/serviceAdvisor/DetailsForm';
import SectionSelection from './pages/serviceAdvisor/SectionSelection';
import AddNewVehicleForm from './pages/serviceAdvisor/AddNewVehicleForm';
import ServiceHistory from './pages/serviceAdvisor/ServiceHistory';
import { getCookie } from './jsfunctions/cookies';
import { useHistory } from 'react-router-dom/cjs/react-router-dom';

import AdminAsignTechnician from './pages/admin/AdminAsignTechnician';
import AdminReports from './pages/admin/AdminReports';
import SearchVehicle from './pages/serviceAdvisor/SearchVehicle';
import BarChart from './components/Moleculars/BarChart';
import DoughnutChart from './components/Moleculars/DoughnutChart';
import Invoice from './components/Moleculars/Invoice';
import Charts from './pages/customer/Charts';
import CheckListPage from './pages/customer/CheckListPage';
import ItemRequest from './pages/serviceAdvisor/ItemRequest';
import Error from './pages/Error';
import InvoiceHistory from './pages/customer/InvoiceHistory';
import RepairInvoice from './pages/serviceAdvisor/RepairInvoice';
import ForgetPassword from './pages/ForgetPassword';
import ForgetPassCodeSubmit from './components/Atoms/ForgetPassCodeSubmit';
import CustomerDetails from './pages/admin/CustomerDetails';
import ItemsAprove from './pages/admin/ItemsAprove';




function App() {
  //redirect to login if token is not set

  // const history=useHistory();

  // if(getCookie('token')==null){
  //   history.push('/login');
  // }

  return (
    <div className="overflow-auto">

      {/* <NavBar/> */}
      <Router>
        <Switch>

          <Route path="/" exact component={Landing} />
          <Route path="/signup" exact component={Signup} />
          <Route path="/login" exact component={Login} />
          <Route path="/bar" exact component={DoughnutChart} />
          <Route path="/pie" exact component={BarChart} />
          <Route path="/invoice" exact component={Invoice} />
          <Route path="/error" exact component={Error} />


          <Route path="/serviceadvisor" exact component={Service} />
          {/* <Route path="/serviceadvisor/addnew" exact component={AddNew} /> */}
          <Route path="/serviceadvisor/detailsform" exact component={DetailsForm} />
          <Route path="/serviceadvisor/addvehicle" exact component={AddNewVehicleForm} />
          <Route path="/serviceadvisor/vehicleinfo" exact component={VehicleInfo} />
          <Route path="/serviceadvisor/section/selection" exact component={SectionSelection} />
          <Route path="/serviceadvisor/servicehistory" exact component={ServiceHistory} />
          <Route path="/serviceadvisor/searchvehicle" exact component={SearchVehicle} />
          <Route path="/serviceadvisor/itemrequests" exact component={ItemRequest} />
          <Route path="/serviceadvisor/invoice" exact component={RepairInvoice} />


          <Route path="/test" exact component={Test} />
          <Route path="/customer" exact component={Customer} />
          <Route path="/customer/vehicle" exact component={VehicleDetails} />
          <Route path="/customer/vehicle/history" exact component={RepairHistory} />
          <Route path="/customer/vehicle/expenses" exact component={Expenses} />
          <Route path="/customer/profile" exact component={Profile} />
          <Route path="/customer/checklist" exact component={CheckListPage} />
          <Route path="/customer/invoice" exact component={InvoiceHistory} />
          <Route path="/customer/details" exact component={Charts} />


          <Route path="/admin" exact component={AdminDashboard} />
          <Route path="/admin/staff" exact component={AdminStaff} />
          <Route path="/admin/staff/:staffid" component={AdminStaff} />
          <Route path="/admin/section" exact component={AdminSection} />
          <Route path="/admin/test" exact component={Testing} />
          <Route path="/admin/technician/asign" exact component={AdminAsignTechnician} />
          <Route path="/admin/customers" exact component={CustomerDetails} />


          <Route path="/technician/dashboard" exact component={Dashboard} />
          <Route path="/technician" exact component={Dashboard} />
          <Route path="/technician/summary" exact component={ServiceSummary} />
          <Route path="/technician/progress" exact component={ServiceProgress} />


          <Route path="/fpass" exact component={ForgetPassword} />
          <Route path="/fpass/code" exact component={ForgetPassCodeSubmit} />
          <Route path="" exact component={Error} />

        </Switch >
      </Router >

    </div >
  );
}

export default App;
