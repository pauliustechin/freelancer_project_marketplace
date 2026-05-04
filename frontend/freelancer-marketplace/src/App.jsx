import { useEffect } from 'react';
import useProjectsStore from './store/projectsStore';
import HomePage from './pages/HomePage';

import './App.css'


function App() {

  const { searchForProjects } = useProjectsStore(state => state);

  useEffect(() => {
    searchForProjects();
  }, [searchForProjects]);

  return (
    <>
    <HomePage></HomePage>
    </>
  )
}

export default App
