import { NavLink } from "react-router";

const Footer = () => {
  const year = new Date().getFullYear();

  return (
    <footer className="bg-slate-800 text-start pt-10">
      <div className="flex gap-15 p-4">
        <div className="w-[35%]">
          <h1 className="text-lg text-white font-bold">
            [logo]FreelancerMarketplace
          </h1>
          <p className="text-gray-400">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis,
            possimus obcaecati eaque itaque molestias dolores quaerat ab fuga
            animi, magnam labore odio dolorum dolore saepe!
          </p>
        </div>
        <nav className="flex flex-col gap-3 text-gray-400">
          <p className="text-white font-bold">For Clients</p>
          <NavLink>How to hire</NavLink>
          <NavLink>Talent Marketplace</NavLink>
          <NavLink>Enterprice solutions</NavLink>
          <NavLink>Pricing</NavLink>
        </nav>
        <nav className="flex flex-col gap-3 text-gray-400">
          <p className="text-white font-bold">For Talent</p>
          <NavLink>How to find work</NavLink>
          <NavLink>Direct Contracts</NavLink>
          <NavLink>Find freelance Jobs</NavLink>
          <NavLink>Community</NavLink>
        </nav>
        <nav className="flex flex-col gap-3 text-gray-400">
          <p className="text-white font-bold">For Clients</p>
          <NavLink>About us</NavLink>
          <NavLink>Careers</NavLink>
          <NavLink>Contact</NavLink>
        </nav>
      </div>

      <div className="p-4">
        <hr className="border-gray-500" />
        <p className="text-gray-400 p-1">{year} Freelancer Marketplace </p>
      </div>
    </footer>
  );
};

export default Footer;
