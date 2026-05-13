import { MdBusinessCenter } from "react-icons/md";
import { CgAddR } from "react-icons/cg";
import { LuBriefcaseBusiness } from "react-icons/lu";


export const clientNavigation = [
  {
    name: "Client Dashboard", 
    href: "/client", 
    icon: MdBusinessCenter,
  }, {
    name: "Create Project", 
    href: "/client/create-project", 
    icon: CgAddR
  }, {
    name: "Contracts", 
    href: "/client/contracts", 
    icon: LuBriefcaseBusiness
  }
]  

export const freelancerNavigation = [
  {
    name: "Freelancer Dashboard", 
    href: "/freelancer", 
    icon: MdBusinessCenter,
  }, {
    name: "Contracts", 
    href: "/freelancer/contracts", 
    icon: LuBriefcaseBusiness
  }
]

export const adminNavigation = [
  {
    name: "Admin Dashboard", 
    href: "/admin", 
    icon: MdBusinessCenter,
  }
]