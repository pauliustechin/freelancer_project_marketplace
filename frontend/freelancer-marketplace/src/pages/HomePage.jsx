import useProjectsStore from '../store/projectsStore';
import { Link } from "react-router";

const HomePage = () => {

const { projects } = useProjectsStore(state => state);

  return (
    <>
    <p>HOME PAGE</p>
    <div><Link to="/login"><button className='border text-xl font-bold '>Please login</button></Link></div>
    <div>{projects.map(project => <p key={project.projectId}>{project.projectName}</p>)}</div>
    </>
    
  )
}

export default HomePage