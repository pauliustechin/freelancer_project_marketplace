import { MdBusinessCenter } from "react-icons/md";
import { CgAddR } from "react-icons/cg";

export const clientNavigation = [
  {
    name: "Client Dashboard", 
    href: "/client", 
    icon: MdBusinessCenter,
  }, {
    name: "Create Project", 
    href: "/client/create-project", 
    icon: CgAddR
  }
]  

export const freelancerNavigation = [
  {
    name: "Freelancer Dashboard", 
    href: "/freelancer", 
    icon: MdBusinessCenter,
  }
]

export const adminNavigation = [
  {
    name: "Admin Dashboard", 
    href: "/admin", 
    icon: MdBusinessCenter,
  }
]