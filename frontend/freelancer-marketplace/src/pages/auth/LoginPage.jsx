import { Link } from "react-router";
import { useForm } from "react-hook-form";
import InputField from "../../components/shared/InputField";
import { useNavigate } from "react-router";
import useUsersStore from "../../store/usersStore";

const LoginPage = () => {

  const navigate = useNavigate();
  const { loginUser } = useUsersStore(state => state);

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm();


  const onSubmit = (formData) => {
    loginUser(formData, navigate);
    reset();
  }


  return (
    <>
      <div>LoginPage</div>
      <div><Link to="/"><button className='border text-xl font-bold'>Go to HomePage</button></Link></div>
      <form onSubmit={handleSubmit(onSubmit)} className=" w-68 border">
        <h2 className="text-2xl font-bold">Please Login</h2>

        <InputField 
          register={register}
          id="username"
          type="text"
          placeholder="username"
          required
          min={3}
          errors={errors}
        />

        <InputField 
          register={register}
          id="password"
          type="password"
          placeholder="password"
          required
          min={8}
          errors={errors}
        />

        <button type="submit" className="my-button">Submit</button>
        <p>Dont have an account? <Link to="/register"><span className="underline">Sign Up</span></Link></p>
      </form>
    </>

  )
}

export default LoginPage;