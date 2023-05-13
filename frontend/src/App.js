import './App.css';
import NavBar from './Home/NavBar'
import HomeDetails from './Home/HomeDetails'
import LogInForm from './Home/LogInForm'
import Layout from "./Layout";
import SignUpForm from './Home/SignUpForm'
import ShowRecipe from './Recipe/ShowRecipe'
import ShowRecipes from './Recipe/ShowRecipes'
import ShowRecipesByTags from './Recipe/ShowRecipesByTags'
import ShowRecipesByMealGroups from './Recipe/ShowRecipesByMealGroups'
import ShowProfile from './Profile/ShowProfile'
import ShowNotifications from './Profile/ShowNotifications'
import ShowHomePage from './Profile/ShowHomePage'
import ShowSettings from './Profile/ShowSettings'
import ShowKolekcija from './Profile/ShowKolekcija'
import AddRecipe from './Profile/AddRecipe'
import RequireAuth from './Home/RequireAuth'
import {Route, Routes} from "react-router-dom";

function App() {

  const components = (
    <div className="content">
      <Routes>
        <Route exact path="/" element={<HomeDetails />} />
        <Route path="/login" element={<LogInForm />} />
        <Route path="/signup" element={<SignUpForm />} />
        <Route path="/trazi/:naziv" element={<ShowRecipes vrsta="trazi"/>} />
        <Route path="/tagovi/:id" element={<ShowRecipesByTags />} />
        <Route path="/grupeJela/:id" element={<ShowRecipesByMealGroups />} />
        <Route path="/recepti/:id" element={<ShowRecipe />} />
        <Route path="/popularni" element={<ShowRecipes vrsta="popularni" />} />
        <Route path="/novi" element={<ShowRecipes vrsta="novi" />} />
        <Route element={<RequireAuth />}>
          <Route path="/profil/:email" element={<ShowProfile />} />
          <Route path="/profil/uredi" element={<ShowSettings />} />
          <Route path="/kolekcija/:id" element={<ShowKolekcija />} />
          <Route path="/obavjestenja" element={<ShowNotifications />} />
          <Route path="/dodaj" element={<AddRecipe />} />
          <Route path="/pocetna" element={<ShowHomePage />} />
        </Route>
      </Routes>
    </div>
  )

  return (
      <>
        <NavBar />
        <Layout components={components} />
      </>
  );
}

export default App;
