import useProjectsStore from '../../store/projectsStore';
import { Link } from 'react-router';

const HomePage = () => {

  const { projects } = useProjectsStore(state => state);

  return (
    <main>
    <p>HOME PAGE</p>
    <div>{projects.map(project => <p key={project.projectId}>{project.projectName}</p>)}</div>
    <Link to="/login"><button>login</button></Link>
    </main>
  )
}

export default HomePage