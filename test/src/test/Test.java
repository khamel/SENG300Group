package test;


/*	SENG300 Project Iteration 1
 * 	Sarah Walker, Christian Chabot, Keith Hamel
 * 
 * 	Input: 	Path to directory containing java files
 * 			Fully qualified data type name
 * 
 * 	Output:	Number of references and declarations of input data type 
 * 
 */
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;

public class Test {
	
	public static String type;
	public static String inputFilePath;
	
	public static int numDeclarations;
	public static int numReferences;
	
	
	public static void main(String[] args) 
	{
		Test(args);
	}

	public Test(String args) {
		getUserInput();
		String source = convertSourceToString();
		parseSource(source);
		printResults();
	}

	/*
	 * 
	 * 
	 */
	//TODO
	public static void getUserInput()
	{
		//TODO - set type in here as well
		type = "int";
		return;
	}
	
	/*
	 * 
	 * 
	 */
	//TODO
	public static String convertSourceToString()
	{
		String source = "public class A { int j; \n ArrayList<String> al = null; \n char a = 'a'; \n String n; \n double i; \n j =1;}";
		return source;

	}

	/*
	 * 
	 * 
	 */
	public static void parseSource(String source)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS9);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(source.toCharArray());
		
		inputFilePath = "/Users/Sarah/Documents/SENG300";
		
		parser.setUnitName(inputFilePath.toString());
		
		// TODO - Source Path
		String[] classPath = {""};				//source class file location
		String[] sourcePath = {inputFilePath};	//source java file location
		
		parser.setEnvironment(classPath, sourcePath, new String[] {"UTF-8"}, true);
		
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		cu.accept(new ASTVisitor()
			{
			/* Primitives
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.VariableDeclarationFragment)
			 */
			
			public boolean visit(VariableDeclarationFragment node)
				{
					ITypeBinding binding = node.getName().resolveTypeBinding();
					if(type.equals(binding.getQualifiedName()))
					{
						numReferences++;
					}
					return true;
				}
			/* Classes - nesting
			 * (non-Javadoc)
			 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.TypeDeclaration)
			 */
			public boolean visit(TypeDeclaration node) 
				{
					ITypeBinding binding = node.getName().resolveTypeBinding();
					if(type.equals(binding.getQualifiedName()))
						{
								numDeclarations++;
						}
					return true;
				}
			
			/* Annotation - @ stuff
			 * (non-Javadoc)
			 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.AnnotationTypeDeclaration)
			 */
			public boolean visit(AnnotationTypeDeclaration node) 
			{
				ITypeBinding binding = node.getName().resolveTypeBinding();
				if(type.equals(binding.getQualifiedName()))
						{
							numDeclarations++;
						}
				return true;
			}
			
			/* Enum - enum Quark ???
			 * (non-Javadoc)
			 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.EnumDeclaration)
			 */
			
			public boolean visit(EnumDeclaration node) 
			{
				ITypeBinding binding = node.getName().resolveTypeBinding();
				if(type.equals(binding.getQualifiedName()))
						{
							numDeclarations++;
						}
				return true;
			}
		});
		
		return;
	}
	
	/*
	 * 
	 * 
	 */
	public static void printResults()
	{
		System.out.println(type + ". Declarations found: " + numDeclarations + "; references found: " + numReferences + ".");
		return;
	}

}
