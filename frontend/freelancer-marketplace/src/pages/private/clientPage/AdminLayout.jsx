
const SideBar = () => {
  return (
    <div className="drawer lg:drawer-open">
      <input id="my-drawer" type="checkbox" className="drawer-toggle" />
      <div className="drawer-content">Main content</div>
      <div className="drawer-side">
        <label htmlFor="my-drawer" className="drawer-overlay"></label>
        <ul className="menu p-4 w-80 min-h-full bg-base-200">
          <li>
            <a>Dashboard</a>
          </li>
          <li>
            <a>Projects</a>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default SideBar;
